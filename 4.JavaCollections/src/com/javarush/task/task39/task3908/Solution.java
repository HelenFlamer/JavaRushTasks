package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("aaccb"));

    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.length() == 0)
            return false;

        s = s.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if (!map.containsKey(ch))
                map.put(ch, 1);
            else {
                int value = map.get(ch);
                map.remove(ch);
                map.put(ch, ++value);
            }
        }
        int oddPairs = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() % 2 == 1)
                oddPairs++;
        }

        return oddPairs <= 1;
    }
}
