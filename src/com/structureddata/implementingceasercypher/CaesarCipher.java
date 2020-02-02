package com.structureddata.implementingceasercypher;

public class CaesarCipher {


    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
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


    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String currChar = Character.toString(input.charAt(i));
            if (i % 2 == 0) {
                encryptedMessage.append(encrypt(currChar, key1));
            }
            else {
                encryptedMessage.append(encrypt(currChar, key2));
            }
        }
        return encryptedMessage.toString();
    }


    public void testCaesar() {
        /*FileResource resource = new FileResource();
        String message = resource.toString();
        int key = 17;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted); */
        //System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        //System.out.println(encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 23));
        System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26 - 2, 26 - 20));
    }


    public static void main(String [] args) {
        CaesarCipher test = new CaesarCipher();
        test.testCaesar();
    }
}
