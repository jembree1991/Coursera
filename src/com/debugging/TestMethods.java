package com.debugging;

public class TestMethods {


    public boolean isAorE(char ch) {
        if (ch == 'a') {
            return true;
        }
        if (ch == 'e') {
            return true;
        }
        return false;
    }

    public static void main(String [] args) {
        TestMethods test = new TestMethods();
        System.out.println(test.isAorE('a'));
    }
}
