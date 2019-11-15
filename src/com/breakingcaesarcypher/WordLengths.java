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
                wordLength--;
            }
            if (!Character.isLetter(firstChar)) {
                wordLength--;
            }
            if (wordLength > 30) {
                wordLength = 30;
            }
            counts[wordLength]++;
        }
        return counts;
    }


    public int indexOfMax(int[] values) {
        int highestWordCount = 0;
        int highestWordCountIndex = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > highestWordCount) {
                highestWordCountIndex = i;
                highestWordCount = values[i];
            }
        }
        return highestWordCountIndex;
    }


    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        int [] currCounts = countWordLengths(fr, counts);
        System.out.println(Arrays.toString(currCounts));
        System.out.println(indexOfMax(countWordLengths(fr, currCounts)));
    }


    public static void main(String [] args) {
        WordLengths test = new WordLengths();
        test.testCountWordLengths();
    }
}
