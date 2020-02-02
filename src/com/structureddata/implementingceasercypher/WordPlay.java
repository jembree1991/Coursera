package com.structureddata.implementingceasercypher;

public class WordPlay {


    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        boolean vowelTest = false;
        String vowels = "aeiou";
        if (vowels.indexOf(ch) != -1) {
            vowelTest =true;
        }
        return vowelTest;
    }


    public void testIsVowel() {
        System.out.println(isVowel('a'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('B'));
        System.out.println(isVowel('b'));
    }


    public String replaceVowels(String phrase, char ch) {
        StringBuilder changedPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char testChar = changedPhrase.charAt(i);
            if (isVowel(testChar)) {
                changedPhrase.setCharAt(i, ch);
            }
        }
        return changedPhrase.toString();
    }


    public void testReplaceVowels() {
        System.out.println(replaceVowels("I hope this works", '*'));
    }


    public String emphasize(String phrase, char ch) {
        StringBuilder changedPhrase = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char testChar = Character.toLowerCase(phrase.charAt(i));
            if (testChar == ch &&  i % 2 == 0) {
                changedPhrase.append('*');
            }
            else if (testChar == ch) {
                changedPhrase.append('+');
            }
            else {
                changedPhrase.append(testChar);
            }
        }
        return changedPhrase.toString();
    }


    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
        System.out.println(emphasize("This should also work", 'o'));

    }


    public static void main(String [] args) {
        WordPlay test = new WordPlay();
        //test.testIsVowel();
        test.testReplaceVowels();
        //test.testEmphasize();
    }
}
