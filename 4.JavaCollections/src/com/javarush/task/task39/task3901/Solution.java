package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int length = 0;
        int currentLength = 0;
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (true) {
                if ( j == s.length() || chars.contains(s.charAt(j)))
                    break;
                chars.add(s.charAt(j));
                currentLength++;
                j++;
            }

            if (currentLength > length)
                length = currentLength;
            currentLength = 0;
            chars.clear();
        }
        return length;
    }
}
