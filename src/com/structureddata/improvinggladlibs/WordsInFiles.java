package com.structureddata.improvinggladlibs;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {


    private HashMap<String, ArrayList<String>> wordList;


    public WordsInFiles() {
        wordList = new HashMap<String, ArrayList<String>>();
    }


    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        ArrayList<String> currList;
        for (String word : fr.words()) {
            if (!wordList.containsKey(word)) {
                ArrayList<String> files = new ArrayList<String>();
                files.add(f.getName());
                wordList.put(word, files);
            }
            else {
                currList = wordList.get(word);
                if (!currList.contains(f.getName())) {
                    currList.add(f.getName());
                    wordList.put(word, currList);
                }
            }
        }
    }


    public void buildWordFileMap() {
        wordList.clear();
        DirectoryResource resource = new DirectoryResource();
        for (File file : resource.selectedFiles()) {
            addWordsFromFile(file);
        }
    }


    public int maxNumber() {
        int largestFileCount = 0;
        for (String word : wordList.keySet()) {
            int currFileCount = wordList.get(word).size();
            if (currFileCount > largestFileCount) {
                largestFileCount = currFileCount;
            }
        }
        return largestFileCount;
    }


    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsWithNumberCount = new ArrayList<String>();
        for (String word : wordList.keySet()) {
            int currFileCount = wordList.get(word).size();
            if (currFileCount == number) {
                wordsWithNumberCount.add(word);
            }
        }
        return wordsWithNumberCount;
    }


    public void printFileIn(String word) {
        if (wordList.containsKey(word)) {
            for (String file : wordList.get(word)) {
                System.out.println(file);
            }
        }
    }


    public void tester() {
        buildWordFileMap();
        //System.out.println(maxNumber());
        //System.out.println(wordsInNumFiles(4).size());
        printFileIn("tree");
        //printFileIn("red");
    }


    public static void main(String [] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }


}
