package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
1. Создай список строк.
2. Добавь в него 10 строчек с клавиатуры.
3. Узнай, какая строка в списке встретится раньше: самая короткая или самая длинная.
Если таких строк несколько, то должны быть учтены самые первые из них.
4. Выведи на экран строку из п.3.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = 0;
        while (j < 10) {
            list.add(reader.readLine());
            j++;
        }
        reader.close();

        int minIndex = 0, maxIndex = 0;
        int minLength = list.get(0).length(), maxLength = list.get(0).length();

        for (int i = 1; i < list.size(); i++) {
            String s = list.get(i);

            if (s.length() < minLength) {
                minLength = s.length();
                minIndex = i;
            }
            if (s.length() > maxLength){
                maxLength = s.length();
                maxIndex = i;
            }
        }

        System.out.print(list.get(Math.min(maxIndex, minIndex)));

    }
}
