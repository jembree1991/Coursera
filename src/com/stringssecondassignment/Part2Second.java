package com.stringssecondassignment;

public class Part2Second {
    public int howMany (String stringa, String stringb) {
        int occurrenceCount = 0;
        int currentIndex = 0;
        while (currentIndex != -1) {
            currentIndex = stringb.indexOf(stringa, currentIndex);
            if (currentIndex != -1) {
                occurrenceCount += 1;
                currentIndex += stringa.length();
            }
        }
        return occurrenceCount;
    }
    public void testHowMany() {
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println(howMany(a, b));
    }
    public static void main(String [] args) {
        Part2Second p2 = new Part2Second();
        p2.testHowMany();
    }
}
