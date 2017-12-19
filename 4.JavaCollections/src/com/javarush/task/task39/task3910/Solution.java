package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.print(isPowerOfThree(0));

    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        return 1162261467 % n == 0;

    }
}
