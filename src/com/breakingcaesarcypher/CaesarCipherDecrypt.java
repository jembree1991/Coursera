package com.breakingcaesarcypher;

import com.implementingceasercypher.CaesarCipher;
import edu.duke.FileResource;

import java.io.File;

public class CaesarCipherDecrypt {

    CaesarCipher cc = new CaesarCipher();

    /**
     * Counts the letters in a string to an array, starting with 'a' count at the 0 index.
     * @param message A {@link String} of any type of characters
     * @return A {@link java.lang.reflect.Array} of the count of each letter of the alphabet from message
     */
    public int[] letterCount(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] letterCount = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char currChar = Character.toLowerCase(message.charAt(i));
            int currCharIndex = alphabet.indexOf(currChar);
            if (currCharIndex != -1) {
                letterCount[currCharIndex] += 1;
            }
        }
        return letterCount;
    }


    public int maxIndex(int[] values) {
        int largestLetterCount = 0;
        int largestCountIndex = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > largestLetterCount) {
                largestCountIndex = i;
                largestLetterCount = values[i];
            }
        }
        return largestCountIndex;
    }


    public String decrypt(String encryptedMessage) {
        int[] letterCount = letterCount(encryptedMessage);
        int indexOfEGuess = maxIndex(letterCount);
        int initialKey = indexOfEGuess - 4;
        if (indexOfEGuess < 4) {
            initialKey = 26 - (4 - indexOfEGuess);
        }
        int reverseKey = 26 - initialKey;
        return cc.encrypt(encryptedMessage, reverseKey);
    }


    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }


    public int getKey(String s) {
        int[] letterCount = letterCount(s);
        int indexOfEGuess = maxIndex(letterCount);
        int initialKey = indexOfEGuess - 4;
        if (indexOfEGuess < 4) {
            initialKey = 26 - (4 - indexOfEGuess);
        }
        return initialKey;
    }


    public String decryptTwoKeys(String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println("key 1: " + key1 + "\n" + "key 2: " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }


    public void testMethods() {
        //int [] testArray = letterCount("aaa bbbb ccc qqqqq");
        //System.out.println(Arrays.toString(testArray));
        //int testCount = maxIndex(testArray);
        //System.out.println(testCount);
        FileResource fr = new FileResource();
        String testString = fr.asString();
        System.out.println(testString);
        //System.out.println(decrypt("Hp xlop l dpyepynp htes wzed zq ppp'd"));
        System.out.println(decryptTwoKeys(testString));
    }


    public static void main(String [] args) {
        CaesarCipherDecrypt test = new CaesarCipherDecrypt();
        test.testMethods();
    }




}
