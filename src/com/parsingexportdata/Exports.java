package com.parsingexportdata;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.function.Predicate;


/**
 * Class responsible for listing export and Value of exports.
 */
public class Exports {

    /**
     * Default constructor.
     * @return
     */
    public CSVParser tester() {
        FileResource fr = new FileResource();
        return fr.getCSVParser();
    }

    /**
     *
     * @param parser the {@link CSVParser} that does the parsing
     * @param country the {@link String} the respresents the contry to ccheck
     * @return
     */
    public String countryInfo(CSVParser parser, String country) {
        String info = "NOT FOUND";
        for (CSVRecord record : parser) {
            String currCountry = record.get("Country");
            if (currCountry.equals(country)) {
                info = (record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return info;
    }


    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String currExports = record.get("Exports");
            if (currExports.contains(exportItem1) && currExports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }


    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countryCount = 0;
        for (CSVRecord record : parser) {
            String currExports = record.get("Exports");
            if (currExports.contains(exportItem)) {
                countryCount += 1;
            }
        }
        return countryCount;
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String currDollars = record.get("Value (dollars)");
            if (currDollars.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    public static void main(String [] args) {
        Exports test = new Exports();
        //System.out.println(test.countryInfo(test.tester(), "Nauru"));
        //test.listExportersTwoProducts(test.tester(), "cotton", "flowers");
        //System.out.println(test.numberOfExporters(test.tester(), "cocoa"));
        test.bigExporters(test.tester(), "$999,999,999,999");
    }
}
