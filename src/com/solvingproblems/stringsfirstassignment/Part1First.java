package com.solvingproblems.stringsfirstassignment;



public class Part1First {

    public String findSimpleGene(String dna) {
        String geneString;
        int startPoint = dna.indexOf("ATG"); // Finds first occurrence of "ATG"
        if (startPoint == -1) {
            return null; // if no occurrence, return null
            }
        int endPoint = dna.indexOf("TAA", startPoint); //find occurrence of ""TAA" after "ATG"
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
        String dnaString = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA string is " + findSimpleGene(dnaString));
        dnaString = "ATGABCABCTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString));
        dnaString = "ABCTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString));
        dnaString = "ATGABC";
        System.out.println("DNA string is " + findSimpleGene(dnaString));
        dnaString = "ATGABCABTAA";
        System.out.println("DNA string is " + findSimpleGene(dnaString));
    }

    public static void main (String [] args) {
        Part1First p1 = new Part1First();
        //p1.testSimpleGene();
    }
}
