package com.breakingcaesarcypher;

import edu.duke.FileResource;

import java.util.Arrays;

public class WordLengths {


    public int[] countWordLengths(FileResource resource, int[] counts) {
        for (String currWord : resource.words()) {
            int wordLength = currWord.length();
            char firstChar = currWord.charAt(0);
            char lastChar = currWord.charAt(currWord.length() - 1);
            if (!Character.isLetter(lastChar)) {
                wordLength -= 1;
            }
            if (!Character.isLetter(firstChar)) {
                wordLength -= 1;
            }
            if (wordLength > 30) {
                wordLength = 30;
            }
            counts[wordLength] += 1;
        }
        return counts;
    }


    public int indexOfMax(int[] values) {
        int highestWordCountIndex = 0;
        for (int currIndex : values) {
            if (values[currIndex] > highestWordCountIndex) {
                highestWordCountIndex = currIndex;
            }
        }
        return highestWordCountIndex;
    }


    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        //int [] currCounts = countWordLengths(fr, counts);
        System.out.println(indexOfMax(countWordLengths(fr, counts)));
    }


    public static void main(String [] args) {
        WordLengths test = new WordLengths();
        test.testCountWordLengths();
    }
}
