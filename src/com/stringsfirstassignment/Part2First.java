package com.stringsfirstassignment;

public class Part2First {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String geneString;
        int startPoint = dna.toUpperCase().indexOf(startCodon); // Finds first occurrence of "ATG"
        if (startPoint == -1) {
            return null; // if no occurrence, return null
        }
        int endPoint = dna.toUpperCase().indexOf(stopCodon, startPoint); //find occurrence of ""TAA" after "ATG"
        if (endPoint == -1) {
            return null; // if not occurrence, returns null
        }
        geneString = dna.substring(startPoint, endPoint + 3);
        if (geneString.length() % 3 != 0) { // checks if substring is a multiple of 3
            return null;
        }
        return geneString; // if "ATG" and "TAA" exist, and is multiple of 3, returns everything between
    }

    public void testSimpleGene() {
        String dnaString = "atgtaa";
        System.out.println("DNA string is " + findSimpleGene(dnaString, "ATG", "TAA"));
        dnaString = "ATGABCABCTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString, "ATG", "TAA"));
        dnaString = "ABCTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString, "ATG", "TAA"));
        dnaString = "ATGABC";
        System.out.println("DNA string is " + findSimpleGene(dnaString, "ATG", "TAA"));
        dnaString = "ATGABCABTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString, "ATG", "TAA"));
    }

    public static void main(String[] args) {
        Part2First p2 = new Part2First();
        p2.testSimpleGene();
    }
}
