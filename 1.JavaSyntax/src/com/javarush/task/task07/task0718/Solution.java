package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
Введи с клавиатуры 10 слов в список строк.
2. Определить, является ли список упорядоченным по возрастанию длины строки.
3. В случае отрицательного ответа вывести на экран номер первого элемента, нарушающего такую упорядоченность.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = 0;
        while (j < 10) {
            list.add(reader.readLine());
            j++;
        }
        reader.close();

        for (int i = 1; i < list.size(); i++){
            if (list.get(i).length() < list.get(i-1).length()){
                System.out.print(i);
                break;
            }
        }
    }
}

