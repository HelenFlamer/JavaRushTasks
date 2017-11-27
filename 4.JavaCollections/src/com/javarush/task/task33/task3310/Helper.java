package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    /**
     * 4.1.1. Добавь в него статический метод String generateRandomString(),
     * который будет генерировать случайную строку. Воспользуйся для этого классами
     * SecureRandom и BigInteger. Подсказка: гугли запрос «random string java«.
     4.1.2. Добавь в класс статический метод printMessage(String message).
     Он должен выводить переданный текст в консоль. Весь дальнейший вывод в
     программе должен быть реализован через этот метод!
     */

    public static String generateRandomString(){
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(100, secureRandom).toString(32);
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
