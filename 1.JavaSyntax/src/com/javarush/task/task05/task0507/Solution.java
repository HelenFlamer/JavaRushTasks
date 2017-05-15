package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int num, count = 0;
        double res = 0;
        while (!(line = reader.readLine()).equals("-1")){

            count++;
            res += Integer.parseInt(line);
        }
        System.out.print(res/count);
    }
}

