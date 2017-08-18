package com.javarush.task.task32.task3213;

import java.io.*;

/* 
Шифр Цезаря
Реализуй логику метода String decode(StringReader reader, int key).
Метод получает данные в закодированном виде.
Он должен вернуть дешифрованную строку, что хранится в StringReader — е.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader != null){
        StringWriter sw = new StringWriter();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (bufferedReader.ready()) {
            int c = bufferedReader.read();

            if (c == -1) break;
           /* if (c < 32) sw.write(c);
            else if (c == 97) sw.write(120);
            else if (c == 98) sw.write(121);
            else if (c == 99) sw.write(122);
            else if (c == 65) sw.write(88);
            else if (c == 66) sw.write(89);
            else if (c == 67) sw.write(90);
            else*/
            sw.write(c + key);
        }

        return sw.toString();}
        else
            return new String();
    }

}
