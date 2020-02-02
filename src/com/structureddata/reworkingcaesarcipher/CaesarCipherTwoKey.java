package com.structureddata.reworkingcaesarcipher;


import edu.duke.FileResource;

public class CaesarCipherTwoKey {


    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;


    public CaesarCipherTwoKey(int key1, int key2) {
        alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;

    }


    public String encrypt(String input) {
        StringBuilder encryptedMessage = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int alphabetIndex = alphabet.indexOf(Character.toUpperCase(currChar));
            if (alphabetIndex != -1) {
                if (i % 2 == 0) {
                    if (Character.isUpperCase(currChar)) {
                        encryptedMessage.setCharAt(i, shiftedAlphabet1.charAt(alphabetIndex));
                    }
                    else {
                        encryptedMessage.setCharAt(i, Character.toLowerCase(shiftedAlphabet1.charAt(alphabetIndex)));
                    }
                } else {
                    if (Character.isUpperCase(currChar)) {
                        encryptedMessage.setCharAt(i, shiftedAlphabet2.charAt(alphabetIndex));
                    }
                    else {
                        encryptedMessage.setCharAt(i, Character.toLowerCase(shiftedAlphabet2.charAt(alphabetIndex)));
                    }
                }
            }
        }
        return encryptedMessage.toString();
    }


    public String decrypt(String input) {
        CaesarCipherTwoKey ccTwo = new CaesarCipherTwoKey(26 - key1, 26 - key2);
        return ccTwo.encrypt(input);
    }


    public int[] letterCount(String message) {
        int[] letterCount = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char currChar = Character.toUpperCase(message.charAt(i));
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


    public String breakCaesarCipher(String input) {
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println(key1 + "," + key2);
        CaesarCipherTwoKey ccTwo = new CaesarCipherTwoKey(key1, key2);
        return ccTwo.decrypt(input);
    }


    public void simpleTest() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = encrypt(input);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }


    public static void main(String [] args) {
        CaesarCipherTwoKey cc = new CaesarCipherTwoKey(17, 4);
        //cc.simpleTest();
        FileResource fr = new FileResource();
        String input = fr.asString();
        //System.out.println(cc.breakCaesarCipher(input));
        System.out.println(cc.encrypt(input));

    }
}
