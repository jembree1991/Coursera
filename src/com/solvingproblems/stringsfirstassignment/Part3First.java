package com.solvingproblems.stringsfirstassignment;

public class Part3First {
    public boolean twoOccurrences (String stringa, String stringb) {
        int occurrenceCount = 0;
        int currentIndex = 0;
        while (currentIndex != -1) {
            currentIndex = stringb.indexOf(stringa, currentIndex);
            if (currentIndex != -1) {
                occurrenceCount += 1;
                currentIndex += 1;
            }
        }
        return occurrenceCount > 1;
    }
    public String lastPart (String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1) {
            return stringb;
        }
        return stringb.substring(startIndex + stringa.length());
    }
    public void testing() {
        String stringa = "a";
        String stringb = "banana";
        System.out.println(stringa  + ", " + stringb + ", " + twoOccurrences(stringa, stringb));
        stringa = "a";
        stringb = "car";
        System.out.println(stringa  + ", " + stringb + ", " + twoOccurrences(stringa, stringb));
        stringa = "b";
        stringb = "absbdbfbgbhbjbkbl";
        System.out.println(stringa  + ", " + stringb + ", " + twoOccurrences(stringa, stringb));
        stringa = "car";
        stringb = "there is a car";
        System.out.println(stringa  + ", " + stringb + ", " + twoOccurrences(stringa, stringb));
        stringa = "an";
        stringb = "banana";
        System.out.println("The part of the string after " + stringa  + " in " + stringb + " is " + lastPart(stringa, stringb));
    }
    public static void main (String [] args) {
        Part3First p3 = new Part3First();
        p3.testing();
    }
}
