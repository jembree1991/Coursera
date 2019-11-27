package com.tellingrandomstories;


import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Arrays;


public class CharactersInPlay {


    private ArrayList<String> characterNames;
    private ArrayList<Integer> nameCount;


    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        nameCount = new ArrayList<Integer>();
    }


    public void update(String person) {
        int nameIndex = characterNames.indexOf(person);
        if (nameIndex == -1) {
            characterNames.add(person);
            nameCount.add(1);
        }
        else {
            int value = nameCount.get(nameIndex);
            nameCount.set(nameIndex, value + 1);
        }
    }


    public void findAllCharacters() {
        characterNames.clear();
        nameCount.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int startPoint = 0;
            int endPoint = line.indexOf('.');
            while (endPoint != -1) {
                String possibleCharacter = line.substring(startPoint, endPoint);
                update(possibleCharacter);
                startPoint = endPoint + 1;
                endPoint = line.indexOf('.', startPoint);
            }
        }
    }


    public void charactersWithNumbParts(int num1, int num2) {
        for (int i = 0; i < nameCount.size(); i++) {
            if (nameCount.get(i) >= num1 && nameCount.get(i) <= num2) {
                System.out.println(characterNames.get(i) + "\t" + nameCount.get(i));
            }
        }
    }


    public void tester() {
        findAllCharacters();
        for (int i = 0; i < nameCount.size(); i++) {
            if (nameCount.get(i) < 100) {
                System.out.println(characterNames.get(i) + "\t" + nameCount.get(i));
            }
        }
        charactersWithNumbParts(10, 15);
    }


    public static void main(String [] args) {
        CharactersInPlay CIP = new CharactersInPlay();
        CIP.tester();
    }


}
