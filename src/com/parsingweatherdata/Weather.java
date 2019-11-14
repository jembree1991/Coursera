package com.parsingweatherdata;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;


public class Weather {

    public CSVParser tester() {     //File Resource with no Parameter
        FileResource fr = new FileResource();
        return fr.getCSVParser();
    }
    public CSVParser testerWithFile(File file) {        //File Resource with Parameter
        FileResource fr = new FileResource(file);
        return fr.getCSVParser();
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {      //Finds Coldest Hour in one file
        CSVRecord lowestTempInDay = null;
        for (CSVRecord currHour : parser) {
            if (lowestTempInDay == null) {
                lowestTempInDay = currHour;
            }
            double checkTemp = Double.parseDouble(currHour.get("TemperatureF"));
            double currMinTemp = Double.parseDouble(lowestTempInDay.get("TemperatureF"));
            if (checkTemp < currMinTemp && checkTemp != -9999) {
                lowestTempInDay = currHour;
            }
        }
        return lowestTempInDay;
    }
    public File fileWithColdestTemperature() {        //Finds coldest Temp in many files, and file name
        CSVRecord lowestTempDay = null;
        File lowestTempDayFile = null;
        DirectoryResource directory = new DirectoryResource();
        for (File file : directory.selectedFiles()) {
            CSVRecord currFile = coldestHourInFile(testerWithFile(file));
            if (lowestTempDay == null) {
                lowestTempDay = currFile;
                lowestTempDayFile = file;
            }
            double checkTemp = Double.parseDouble(currFile.get("TemperatureF"));
            double currMinTemp = Double.parseDouble(lowestTempDay.get("TemperatureF"));
            if (checkTemp < currMinTemp) {
                lowestTempDay = currFile;
                lowestTempDayFile = file;
            }
        }
        return lowestTempDayFile;
    }
    public void testFileWithColdestTemperature() {
        File coldestTemp = fileWithColdestTemperature();
        System.out.println(coldestHourInFile(testerWithFile(coldestTemp)));
    }
    public double averageTemperatureInFile(CSVParser parser) {      //Finds average temp of one file
        int tempRecordingCount = 0;
        double totalTemps = Double.MIN_VALUE;
        for (CSVRecord currHour : parser) {
            if (!currHour.get("Humidity").equals("N/A")) {
                totalTemps += Double.parseDouble(currHour.get("TemperatureF"));
                tempRecordingCount += 1;
            }
        }
        return totalTemps / tempRecordingCount;
    }
    public void testAverageTemperatureInFile() {
        double averageTemp = averageTemperatureInFile(tester());
        System.out.println("Average Temp in file is " + averageTemp);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) { //Finds average temp when humid > value
        int tempRecordingCount = 0;
        double totalTemps = Double.MIN_VALUE;
        for (CSVRecord currHour : parser) {
            double currTemp = Double.parseDouble(currHour.get("TemperatureF"));
            if (currTemp != -9999 && !currHour.get("Humidity").equals("N/A")) {
                double currHumid = Double.parseDouble(currHour.get("Humidity"));
                if (currHumid >= value) {
                    totalTemps += currTemp;
                    tempRecordingCount += 1;
                }
            }
        }
        return totalTemps / tempRecordingCount;
    }
    public void testAverageTemperatureWithHighHumidityInFile(int value) {
        double averageTemp = averageTemperatureWithHighHumidityInFile(tester(), value);
        if (averageTemp == Double.POSITIVE_INFINITY) {
            System.out.println("No Temps with that humidity");
        }
        else {
            System.out.println("Average Temp when high humidity is " + averageTemp);
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {       //Finds Lowest humid in one file
        CSVRecord lowestHumidDay = null;
        for (CSVRecord currDay : parser) {
            if (lowestHumidDay == null) {
                lowestHumidDay = currDay;
            }
            if (!currDay.get("Humidity").equals("N/A")) {
                double checkHumid = Double.parseDouble(currDay.get("Humidity"));
                double currMinHumid = Double.parseDouble(lowestHumidDay.get("Humidity"));
                if (checkHumid < currMinHumid) {
                    lowestHumidDay = currDay;
                }
            }
        }
        return lowestHumidDay;
    }
    public void testLowestHumidityInFile() {
        CSVRecord lowestHumid = lowestHumidityInFile(tester());
        System.out.println("Lowest Humidity was " + lowestHumid.get("Humidity") + " at " + lowestHumid.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles() {      //Finds lowest humid in many files
        CSVRecord lowestHumidDay = null;
        DirectoryResource directory = new DirectoryResource();
        for (File file : directory.selectedFiles()) {
            CSVRecord currFile = lowestHumidityInFile(testerWithFile(file));
            if (lowestHumidDay == null) {
                lowestHumidDay = currFile;
            }
            double checkHumid = Double.parseDouble(currFile.get("Humidity"));
            double currMinHumid = Double.parseDouble(lowestHumidDay.get("Humidity"));
            if (checkHumid < currMinHumid) {
                lowestHumidDay = currFile;
            }
        }
        return lowestHumidDay;
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumid = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumid.get("Humidity") + " at " + lowestHumid.get("DateUTC"));
    }
    public static void main(String [] args) {
        Weather test = new Weather();
        //System.out.println(test.coldestHourInFile(test.tester()).get("TemperatureF"));
        test.testFileWithColdestTemperature();
        //test.testLowestHumidityInFile();
        //test.testLowestHumidityInManyFiles();
        //test.testAverageTemperatureInFile();
        //test.testAverageTemperatureWithHighHumidityInFile(80);
    }
}
