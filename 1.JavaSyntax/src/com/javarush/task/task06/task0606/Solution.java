package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = reader.readLine();
        reader.close();


        while (line.length() > 0){
            if (Integer.parseInt(line)%2 == 0)
                even++;
            else odd++;
            line = line.substring(0, line.length()-1);
        }
        System.out.format( "Even: %d Odd: %d", even, odd);
    }
}
