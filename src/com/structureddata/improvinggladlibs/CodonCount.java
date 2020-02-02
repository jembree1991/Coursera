package com.structureddata.improvinggladlibs;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {


    private HashMap<String, Integer> codons;


    public CodonCount() {
        codons = new HashMap<String, Integer>();
    }


    public void buildCodonMap(int start, String dna) {
        codons.clear();
        dna = dna.trim();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String currCodon = dna.substring(i, i + 3);
            if (!codons.containsKey(currCodon)) {
                codons.put(currCodon, 1);
            }
            else {
                codons.put(currCodon, codons.get(currCodon) + 1);
            }
        }
        System.out.println("Reading frame starting with " + start + " results in " + codons.size() + " unique codons");
    }


    public String getMostCommonCodon() {
        int largestCount = 0;
        String codonOfLargestCount = "";
        for (String s : codons.keySet()) {
            if (codons.get(s) > largestCount) {
                largestCount = codons.get(s);
                codonOfLargestCount = s;
            }
        }
        return codonOfLargestCount;
    }


    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
        for (String s : codons.keySet()) {
            if (codons.get(s) >= start && codons.get(s) <= end) {
                System.out.println(s + " " + codons.get(s));
            }
        }
    }


    public void tester() {
        FileResource fr = new FileResource();
        String dnaString = fr.asString();
        buildCodonMap(0, dnaString);
        /*for(String s : codons.keySet()){
            System.out.println(s + "\t" + codons.get(s));
        }*/
    }


    public static void main(String [] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
        String largestCodon = cc.getMostCommonCodon();
        System.out.println("Most Common Codon is " + largestCodon + " with count " + cc.codons.get(largestCodon));
        cc.printCodonCounts(7, 8);
    }


}
