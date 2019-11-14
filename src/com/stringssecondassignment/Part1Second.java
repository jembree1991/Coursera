package com.stringssecondassignment;

import java.util.ArrayList;
import java.util.List;

public class Part1Second {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        while (stopIndex != -1 && (stopIndex - startIndex) % 3 != 0) {
            stopIndex += 1;
            stopIndex = dna.indexOf(stopCodon, stopIndex);
        }
        return stopIndex;
    }
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        List<String> stopCodons = new ArrayList<>();
        stopCodons.add("TAA");
        stopCodons.add("TAG");
        stopCodons.add("TGA");
        String geneString = "";
        int stopIndex = Integer.MAX_VALUE;
        if (startIndex != -1) {
            for (String stopCodon : stopCodons) {
                int currIndex = findStopCodon(dna, startIndex, stopCodon);
                if (currIndex != -1 && currIndex < stopIndex) {
                    stopIndex = currIndex;
                }
            }
        }
        if (stopIndex < dna.length()) {
            geneString = dna.substring(startIndex, stopIndex + 3);
        }
        return geneString;
    }
    public void testFindStopCodon() {
        String testDna = "ATGABCABCTAA";
        System.out.println(findStopCodon(testDna, 0,"TAA"));
        testDna = "ABCABCABCTAG";
        System.out.println(findStopCodon(testDna, 0,"TAG"));
        testDna = "ATGABCABTAA";
        System.out.println(findStopCodon(testDna, 0,"TAA"));
        testDna = "ATGABCABCABC";
        System.out.println(findStopCodon(testDna, 0,"TAA"));
    }
    public void testFindGene() {
        String dnaString = "ABCABCABCTAA";
        //System.out.println("DNA string is " + dnaString + "\n" + "Found Gene is " + findGene(dnaString));
        dnaString = "ATGABCABCABCTAA";
        //System.out.println("DNA string is " + dnaString + "\n" + "Found Gene is " + findGene(dnaString));
        dnaString = "ATGABCABCABTAACTAG";
        //System.out.println("DNA string is " + dnaString + "\n" + "Found Gene is " + findGene(dnaString));
        dnaString = "ATGABCABCTGAABCTAA";
        //System.out.println("DNA string is " + dnaString + "\n" + "Found Gene is " + findGene(dnaString));
        dnaString = "ATGABCABCABC";
        //System.out.println("DNA string is " + dnaString + "\n" + "Found Gene is " + findGene(dnaString));
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        String currGene = findGene(dna);
        while (!currGene.equals("")) {
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            System.out.println(startIndex);
            currGene = findGene(dna.substring(startIndex));
        }
    }
    public static void main(String [] args) {
        Part1Second p1 = new Part1Second();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        p1.printAllGenes("ABCABCABCTAAATGABCABCABCTAAATGABCABCABTAACTAGATGABCABCTGAABCTAAATGABCABCABC");
    }
}
