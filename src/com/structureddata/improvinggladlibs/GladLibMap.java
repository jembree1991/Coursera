package com.structureddata.improvinggladlibs;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {


    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedCats;


    private Random myRandom;

    private int changeCount;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "./TestFiles/GladLibData/data";


    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedCats = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }


    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }


    private void initializeFromSource(String source) {
        String[] categories = new String[]{"adjective", "noun", "color", "country", "name", "animal", "timeframe",
                "verb", "fruit"};
        for (String category : categories) {
            ArrayList<String> currCat;
            currCat = readIt(source + "/" + category + ".txt");
            myMap.put(category, currCat);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        if (!usedCats.contains(label)) {
            usedCats.add(label);
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        changeCount += 1;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for (String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }


    public int totalWordsInMap() {
        int totalWords = 0;
        for (String category : myMap.keySet()) {
            totalWords += myMap.get(category).size();
        }
        return totalWords;
    }


    public int totalWordsConsidered() {
        int totalWordsInCats = 0;
        for (String category : usedCats) {
            totalWordsInCats += myMap.get(category).size();
        }
        return totalWordsInCats;
    }


    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println(totalWordsInMap());
        System.out.println(totalWordsConsidered());

    }


    public static void main(String [] args) {
        GladLibMap lib = new GladLibMap();
        lib.makeStory();
        //System.out.println(lib.changeCount);
    }
}
