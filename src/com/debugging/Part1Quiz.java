package com.debugging;

public class Part1Quiz {

    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            //System.out.println("index " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            //System.out.println("index after update " + index);
        }
    }
    public void test() {
        //findAbc("kdabcabcjei");
        //findAbc("ttabcesoeiabco");
        //findAbc("abcbabccabcd");
        //findAbc("qwertyabcuioabcp");
        findAbc("abcabcabcabca");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    }
    public static void main(String [] args) {
        Part1Quiz p1 = new Part1Quiz();
        p1.test();
    }
}
