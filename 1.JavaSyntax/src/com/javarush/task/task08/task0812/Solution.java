package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int j = 0;
        while (j < 10){
            list.add(Integer.parseInt(reader.readLine()));
            j++;
        }
        reader.close();

        int maxCount = 0;
        int currentCount = 1;
        int flag = list.get(0);
        for (int i = 1; i < list.size(); i++){
            if (flag == list.get(i)){
                currentCount++;
                if (currentCount > maxCount)
                    maxCount = currentCount;
            }
            else {
                flag = list.get(i);
                currentCount = 1;
                if (currentCount > maxCount)
                    maxCount = currentCount;
            }
        }
        System.out.print(maxCount);

    }
}