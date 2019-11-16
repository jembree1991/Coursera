package com.reworkingcaesarcipher;

import edu.duke.FileResource;

public class CaesarCipherOneKey {


    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;


    public CaesarCipherOneKey(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }


    public String encrypt(String input) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int alphabetIndex = alphabet.indexOf(Character.toUpperCase(currChar));
            if (alphabetIndex == -1) {
                encryptedMessage.append(currChar);
            }
            else {
                if (Character.isUpperCase(currChar)) {
                    encryptedMessage.append(shiftedAlphabet.charAt(alphabetIndex));
                }
                else {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(alphabetIndex)));
                }
            }
        }
        return encryptedMessage.toString();
    }


    public String decrypt(String input) {
        CaesarCipherOneKey cc = new CaesarCipherOneKey(26 - mainKey);
        return cc.encrypt(input);
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


    public String breakCaesarCipher(String input) {
        int[] letterCounts = letterCount(input);
        int indexOfEGuess = maxIndex(letterCounts);
        int initialKey = indexOfEGuess - 4;
        if (indexOfEGuess < 4) {
            initialKey = 26 - (4 - indexOfEGuess);
        }
        CaesarCipherOneKey cc = new CaesarCipherOneKey(initialKey);
        return cc.decrypt(input);
    }


    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = encrypt(input);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted);
        //System.out.println(decrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }


    public static void main(String [] args) {
        CaesarCipherOneKey cipher = new CaesarCipherOneKey(15);
        CaesarCipherOneKey cipher2 = new CaesarCipherOneKey(18);
        cipher.simpleTests();
        //cipher2.simpleTests();

    }
}
