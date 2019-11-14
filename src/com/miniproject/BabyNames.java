package com.miniproject;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Class used to find relationships between names in different years and decades
 */

public class BabyNames {


    public String fileLocation = "./TestFiles/us_babynames/us_babynames_by_year/";


    /**
     * Prints total births, births for girls and boys, total girl names, boy names, and total names in a given file.
     */
    public void printNames() {
        int totalBirths = 0;
        int girlsBirths = 0;
        int boysBirths = 0;
        int girlsNamesCount = 0;
        int boysNamesCount = 0;
        int totalNameCount;
        FileResource resource = new FileResource();
        for (CSVRecord record : resource.getCSVParser(false)) {
            int currBirths = Integer.parseInt(record.get(2));
            totalBirths += currBirths;
            if (record.get(1).equals("M")) {
                boysBirths += currBirths;
                boysNamesCount += 1;
            }
            else {
                girlsBirths += currBirths;
                girlsNamesCount += 1;
            }
        }
        totalNameCount = girlsNamesCount + boysNamesCount;
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Girls Births: " + girlsBirths);
        System.out.println("Boys Births: " + boysBirths);
        System.out.println("Girl Names: " + girlsNamesCount);
        System.out.println("Boy Names: " + boysNamesCount);
        System.out.println("Total Names: " + totalNameCount);
    }


    /**
     * Returns popularity of the name for specific gender and year.
     * @param year An integer the year to search
     * @param name A {@link String} representing the name of the baby
     * @param gender A {@link String} of either M or F, representing the gender of the baby
     * @return Returns an integer rank for specific gender, returning -1 if name for gender is not found
     */
    public int getRank(int year, String name, String gender) {
        int rankOfNameInFile = -1;
        int currRankGender = 0;
        String fileName = String.format("yob%s.csv", year);
        FileResource resource = new FileResource(fileLocation + fileName);
        for (CSVRecord record : resource.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                currRankGender += 1;
            }
            if(record.get(0).equals(name) && record.get(1).equals(gender)) {
                rankOfNameInFile = currRankGender;
                return rankOfNameInFile;
            }
        }
        return rankOfNameInFile;
    }


    public String getName(int year, String gender, int rank) {
        String nameAtRank = "NO NAME";
        if (rank == -1) {
            return nameAtRank;
        }
        int currRank = 0;
        String fileName = String.format("yob%s.csv", year);
        FileResource resource = new FileResource(fileLocation + fileName);
        for (CSVRecord record : resource.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                currRank += 1;
            }
            if (currRank == rank && record.get(1).equals(gender)) {
                nameAtRank = record.get(0);
                return nameAtRank;
            }
        }
        return nameAtRank;
    }


    public void whatIsYourNameInYear(int year, int newYear, String name,  String gender) {
       int nameRank = getRank(year, name, gender);
       String nameInNewYear = getName(newYear, gender, nameRank);
       String genderPronoun = "he";
       if (gender.equals("F")) {
           genderPronoun = "she";
       }
        System.out.println(name + " born in " + year + " would be " +
                nameInNewYear + " if " + genderPronoun + " was born in " + newYear);
    }


    public int yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        int year = -1;
        DirectoryResource directory = new DirectoryResource();
        for (File file : directory.selectedFiles()) {
            int yearOfFile = Integer.parseInt(file.getName().substring(3,7));
            int currRank = getRank(yearOfFile, name, gender);
            if (highestRank == -1) {
                highestRank = currRank;
                year = yearOfFile;
            }
            if (currRank < highestRank && currRank != -1) {
                highestRank = currRank;
                year = yearOfFile;
            }
        }
        return year;
    }


    public double getAverageRank(String name, String gender) {
        int totalRank = 0;
        int filesChecked = 0;
        DirectoryResource directory = new DirectoryResource();
        for (File file : directory.selectedFiles()) {
            int yearOfFile = Integer.parseInt(file.getName().substring(3,7));
            int currRank = getRank(yearOfFile, name, gender);
            if (currRank != -1) {
                totalRank += currRank;
            }
            filesChecked += 1;
        }
        double averageRank = (double)totalRank / filesChecked;
        if (averageRank == 0) {
            averageRank = -1;
            return averageRank;
        }
        return averageRank;
    }


    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        String fileName = String.format("yob%s.csv", year);
        FileResource resource = new FileResource(fileLocation + fileName);
        for (CSVRecord record : resource.getCSVParser(false)) {
            int currBirths = Integer.parseInt(record.get(2));
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                return totalBirths;
            }
            else if (record.get(1).equals(gender)) {
                totalBirths += currBirths;
            }
        }
        return totalBirths;
    }


    public static void main(String [] args) {
        BabyNames test = new BabyNames();
        //test.printNames();
        //System.out.println("Name Rank: " + test.getRank(1971, "Frank", "M"));
        //System.out.println(test.getName(1982, "M", 450));
        test.whatIsYourNameInYear(1991, 1976, "Justin", "M");
        //System.out.println(test.yearOfHighestRank("Justin", "M"));
        //System.out.println(test.getAverageRank("Robert", "M"));
        //System.out.println(test.getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
