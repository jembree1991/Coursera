package com.solvingproblems.stringsthirdassignment;

import edu.duke.StorageResource;

import java.util.ArrayList;
import java.util.List;

public class Part1Third {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        while (stopIndex != -1 && (stopIndex - startIndex) % 3 != 0) {
            stopIndex += 1;
            stopIndex = dna.indexOf(stopCodon, stopIndex);
        }
        return stopIndex;
    }
    public String findGene(String dna) {
        dna = dna.toUpperCase();
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
    public void printAllGenes(String dna) {
        StringBuilder oldGenes = new StringBuilder();
        String currGene = findGene(dna.substring(oldGenes.length()));
        while (!currGene.equals("")) {
            System.out.println(currGene);
            oldGenes.append(currGene);
        }
    }
    public StorageResource getAllGenes(String dna) {
        dna = dna.toUpperCase();
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        String currGene = findGene(dna);
        while (!currGene.equals("")) {
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            currGene = findGene(dna.substring(startIndex));
        }
        return geneList;
    }
    public static void main(String [] args) {
        Part1Third p1 = new Part1Third();
        System.out.println(p1.getAllGenes("oneAtGMyGeneOneAAACCCGGYGGGGTAGGGCTGCCCATGendTAAnonCodingDNATAGTGAZZZTAAtwoATGMyGeneTwoCCCCCCatgCCCFalseStartTAATGATGendtwoTAG").data());
    }
}
