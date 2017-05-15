package com.javarush.task.task01.task0130;

/* 
Наш первый конвертер!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertCelsiumToFahrenheit(40));
    }

    public static double convertCelsiumToFahrenheit(int celsium) {
        //TC = (TF – 32) * 5/9
        //напишите тут ваш код
        return 1.8 * celsium + 32;
    }
}