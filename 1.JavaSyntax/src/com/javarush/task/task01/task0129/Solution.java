package com.javarush.task.task01.task0129;

/* 
Считаем длину окружности
*/

import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) {
        printCircleLength(5);
    }

    public static void printCircleLength(int radius) {
        //напишите тут ваш код
        double res =  Math.PI * 2 * radius;
        System.out.format("%.2f", res);
    }
}