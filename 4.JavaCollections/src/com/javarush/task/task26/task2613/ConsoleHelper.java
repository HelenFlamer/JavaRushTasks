package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            String readedString = bis.readLine();
            return readedString;
        } catch (IOException ignore) {
        }
        return null;
    }

    public static String askCurrencyCode() {
        writeMessage("Enter currency code");
        String currencyCode = readString();
        while (currencyCode.length() != 3) {
            writeMessage("Please enter correct currency code");
            currencyCode = readString();
        }

        return currencyCode.toUpperCase();

    }

    public static String[] getValidTwoDigits(String currencyCode) {
        String[] array;
        writeMessage("enter denomination and count");

        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage("invalid data");
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage("invalid data");
                continue;
            }
            break;
        }
        return array;
    }

}
