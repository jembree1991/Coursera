package com.readinglogfiles;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer {


    private ArrayList<LogEntry> records;


    public LogAnalyzer() {
        records = new ArrayList<>();
    }


    private void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry currEntry = WebLogParser.parseEntry(line);
            records.add(currEntry);
        }
    }



    public void printAllStatusHigherThanNum(int num) {
        for (LogEntry entry : records) {
            int currStatus = entry.getStatusCode();
            if (currStatus > num) {
                System.out.println(entry);
            }
        }
    }


    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {   // someday must be in the form "Mmm DD"
        ArrayList<String> visits = new ArrayList<>();
        for (LogEntry entry : records) {
            String currIP = entry.getIpAddress();
            String currDate = entry.getAccessTime().toString();
            if (currDate.contains(someday) && !visits.contains(currIP)) {
                visits.add(currIP);
            }
        }
        return visits;
    }


    public int countUniqueIPStatusInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry entry : records) {
            int currStatus = entry.getStatusCode();
            String currIP = entry.getIpAddress();
            if (currStatus >= low && currStatus <= high && !uniqueIPs.contains(currIP)) {
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }


    private HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipVisits = new HashMap<>();
        for (LogEntry entry : records) {
            String currIP = entry.getIpAddress();
            if (!ipVisits.containsKey(currIP)) {
                ipVisits.put(currIP, 1);
            }
            else {
                ipVisits.put(currIP, ipVisits.get(currIP) + 1);
            }
        }
        return ipVisits;
    }


    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int maxVisit = 0;
        for (int currVisitCount : map.values()) {
            if (currVisitCount > maxVisit) {
                maxVisit = currVisitCount;
            }
        }
        return maxVisit;
    }


    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> maxIPs = new ArrayList<>();
        int maxVisits = mostNumberVisitsByIP(map);
        for (String currIP : map.keySet()) {
            if (map.get(currIP).equals(maxVisits)) {
                maxIPs.add(currIP);
            }
        }
        return maxIPs;
    }


    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> accessDatesAndIPs = new HashMap<>();
        for(LogEntry entry : records) {
            String currDay = entry.getAccessTime().toString().substring(4, 10);
            String currIP = entry.getIpAddress();
            if (!accessDatesAndIPs.containsKey(currDay)) {
                ArrayList<String> currList = new ArrayList<>();
                currList.add(currIP);
                accessDatesAndIPs.put(currDay, currList);
            }
            else {
                ArrayList<String> currList = accessDatesAndIPs.get(currDay);
                currList.add(currIP);
            }
        }
        return accessDatesAndIPs;
    }


    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        String highestVisitDate = "";
        int highestVisitCount = 0;
        for (String day : map.keySet()) {
            int currVisitCount = map.get(day).size();
            if (currVisitCount > highestVisitCount) {
                highestVisitCount = currVisitCount;
                highestVisitDate = day;
            }
        }
        return highestVisitDate;
    }


    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        HashMap<String, Integer> ipCount = new HashMap<>();
        ArrayList<String> mostIPs;
        for (String currIP : map.get(day)) {
            if (!ipCount.containsKey(currIP)) {
                ipCount.put(currIP, 1);
            }
            else {
                ipCount.put(currIP, ipCount.get(currIP) + 1);
            }
        }
        mostIPs = iPsMostVisits(ipCount);
        return mostIPs;
    }


    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


    public void tester() {
        String weblog = "/home/justin/IdeaProjects/Coursera/TestFiles/UniqueIPData/weblog3-short_log";
        String shorttest = "/home/justin/IdeaProjects/Coursera/TestFiles/UniqueIPData/short-test_log";
        String longtest = "/home/justin/IdeaProjects/Coursera/TestFiles/UniqueIPData/weblog2_log.txt";
        readFile(longtest);
        countVisitsPerIP();
        //System.out.println(countVisitsPerIP().size());
        //System.out.println(uniqueIPVisitsOnDay("Sep 27").size());
        //printAll();
        //System.out.println();
        //System.out.println(countVisitsPerIP());
        //System.out.println(countUniqueIPStatusInRange(400, 499));
        //System.out.println(mostNumberVisitsByIP(countVisitsPerIP()));
        //System.out.println(iPsMostVisits(countVisitsPerIP()));
        //System.out.println(iPsForDays());
        //System.out.println(dayWithMostIPVisits(iPsForDays()));
        System.out.println(iPsWithMostVisitsOnDay(iPsForDays(), "Sep 30"));
    }


    public static void main(String [] args) {
        LogAnalyzer la = new LogAnalyzer();
        la.tester();
    }


}
