package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
1. Создать массив на 15 целых чисел.
2. Ввести в него значения с клавиатуры.
3. Пускай индекс элемента массива является номером дома, а значение — число жителей, проживающих в доме.
Дома с нечетными номерами расположены на одной стороне улицы, с четными — на другой. Выяснить, на какой стороне улицы проживает больше жителей.
4. Вывеси на экран сообщение: «В домах с нечетными номерами проживает больше жителей.» или «В домах с четными номерами проживает больше жителей.»

Примечание:
дом с порядковым номером 0 считать четным.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int [] mass = new int[15];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null && i<15){
            mass[i] = Integer.parseInt(line);
            i++;
        }
        reader.close();

        int count1 = 0, count2 = 0;
        for (int j = 0; j < mass.length; j++){
            if (j%2 == 0)
                count2+=mass[j];
            else count1 += mass[j];
        }
        if (count1 > count2)
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        else
            System.out.println("В домах с четными номерами проживает больше жителей.");

    }
}
