package com.structureddata.tellingrandomstories;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {


    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;


    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }


    public void findUnique() {
        myFreqs.clear();
        myWords.clear();
        FileResource resource = new FileResource();
        for (String currWord : resource.words()) {
            currWord = currWord.toLowerCase();
            int currIndex = myWords.indexOf(currWord);
            if (currIndex == -1) {
                myWords.add(currWord);
                myFreqs.add(1);
            }
            else {
                int currValue = myFreqs.get(currIndex);
                myFreqs.set(currIndex, currValue+1);
            }
        }
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
    }


    public int findIndexOfMax() {
        int largestIndex = 0;
        int largestCount = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > largestCount) {
                largestCount = myFreqs.get(i);
                largestIndex = myFreqs.indexOf(largestCount);
            }
        }
        return largestIndex;
    }


    public void tester() {
        findUnique();
        System.out.println(myWords.size());
        int maxIndex = findIndexOfMax();
        System.out.println("Most often word and its count is: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }


    public static void main(String [] args) {
        WordFrequencies words = new WordFrequencies();
        words.tester();
    }


}
