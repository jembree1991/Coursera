package com.structureddata.vigenereknownlanguageandkeylen;


import java.io.File;
import java.util.*;
import edu.duke.*;


public class VigenereBreaker {



    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }


    public char mostCommonCharIn(HashSet<String> dictionary) {
        int largestCharCount = 0;
        char mostCommonChar = 0;
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(!charCount.containsKey(currChar)) {
                    charCount.put(currChar, 1);
                }
                else {
                    charCount.put(currChar, charCount.get(currChar) + 1);
                }
            }
        }
        for (char currChar : charCount.keySet()) {
            if (charCount.get(currChar) > largestCharCount) {
                largestCharCount = charCount.get(currChar);
                mostCommonChar = currChar;
            }
        }
        return mostCommonChar;
    }


    public int countWords(String message, HashSet<String> dictionary) {
        int realWordCount = 0;
        for (String word : message.split("\\W")) {
            if (dictionary.contains(word.toLowerCase())) {
                realWordCount++;
            }
        }
        return realWordCount;
    }


    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedString = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slicedString.append(message.charAt(i));
        }
        return slicedString.toString();
    }


    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker cc = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String currSlice = sliceString(encrypted, i, klength);
            int currKey = cc.getKey(currSlice);
            key[i] = currKey;
        }
        return key;
    }


    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int largestWordCount = 0;
        String bestDecrypt = "";
        int [] bestKey = new int[0];
        for (int i = 1; i <= 100; i++) {
            int[] currKey = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(currKey);
            String currDecrypted = vc.decrypt(encrypted);
            int currWordCount = countWords(currDecrypted, dictionary);
            if (currWordCount > largestWordCount) {
                largestWordCount = currWordCount;
                bestDecrypt = currDecrypted;
                bestKey = currKey;
            }
        }
        System.out.println("Matching words: " + largestWordCount);
        System.out.println("Keys and Array size: " + Arrays.toString(bestKey) + ", " + bestKey.length);
        System.out.println();
        return bestDecrypt;
    }


    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int largestWordCount = 0;
        String bestDecrypt = "";
        String bestLanguage = "";
        for (String language : languages.keySet()) {
            System.out.println("Curr language check: " + language);
            HashSet<String> currDictionary = languages.get(language);
            String currDecrypt = breakForLanguage(encrypted, currDictionary);
            int currWordCount = countWords(currDecrypt, currDictionary);
            if (currWordCount > largestWordCount) {
                largestWordCount = currWordCount;
                bestDecrypt = currDecrypt;
                bestLanguage = language;
            }
        }
        System.out.println("Best Language: " + bestLanguage);
        System.out.println();
        return bestDecrypt;
    }


    public void breakVigenere () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        /*String language = "English";
        FileResource dictResource = new FileResource(
                "/home/justin/IdeaProjects/Coursera/TestFiles/dictionaries/"+language);
        HashSet<String> dictionary = readDictionary(dictResource);*/
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
        for (File file : dr.selectedFiles()) {
            FileResource resource = new FileResource(file);
            HashSet<String> currDictionary = readDictionary(resource);
            dictionaries.put(file.getName(), currDictionary);
        }
        System.out.println(breakForAllLangs(input, dictionaries));
        /*String bestDecrypt = breakForLanguage(input, dictionary);
        System.out.println(bestDecrypt);
        int[] testKey = tryKeyLength(input, 38, 'e');
        VigenereCipher vc = new VigenereCipher(testKey);
        String testOutput = vc.decrypt(input);
        System.out.println(countWords(testOutput, dictionary));*/

    }


    public void tester() {
        breakVigenere();
    }


    public static void main(String [] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.tester();
    }


}
