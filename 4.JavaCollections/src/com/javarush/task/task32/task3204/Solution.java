package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        int numCount = (int) (Math.random() * 5) + 1;
        int lowCharCount = (int) (Math.random() * (6 - numCount)) + 1;
        int bigCharCount = 8 - numCount - lowCharCount;
        System.out.println(numCount + " " + lowCharCount + " " + bigCharCount);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < numCount; i++){
            list.add((int) (Math.random() * 10) + 48);
        }
        for (int i = 0; i < lowCharCount; i++){
            list.add((int) (Math.random() * 26) + 65);
        }
        for (int i = 0; i < bigCharCount; i++){
            list.add((int) (Math.random() * 26) + 97);
        }
        Collections.shuffle(list);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        for (Integer in : list)
            stream.write(in);
        return stream;
    }
}