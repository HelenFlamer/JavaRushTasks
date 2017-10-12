package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        Set<Character> charset = new TreeSet<>();
        while (reader.ready()) {
            String s = reader.readLine().toLowerCase().replaceAll("[^a-z]", "");
            for (int i = 0; i < s.length(); i++){
                if (!charset.contains(s.charAt(i)))
                    charset.add(s.charAt(i));
            }
        }
        reader.close();

        Iterator<Character> it = charset.iterator();
        int index = 0;

        while(it.hasNext() && index < 5) {
            System.out.print(it.next());
            index++;
        }

    }
}
