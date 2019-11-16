package com.reworkingcaesarcipher;


import edu.duke.FileResource;

public class CaesarCipherTwoKey {


    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;


    public CaesarCipherTwoKey(int key1, int key2) {
        alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
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


    public void simpleTest() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        String halfEncrypted = encrypt(input);
        System.out.println(halfEncrypted);
    }


    public static void main(String [] args) {
        CaesarCipherTwoKey cc = new CaesarCipherTwoKey(3, 18);
        cc.simpleTest();

    }
}
