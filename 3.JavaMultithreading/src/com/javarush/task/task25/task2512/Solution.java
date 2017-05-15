package com.javarush.task.task25.task2512;

import java.util.*;

/*
Живем своим умом
В классе Solution реализуй интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений, начиная с самого вложенного.

Пример исключения:
new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))

Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
Требования:
1. Класс Solution должен реализовывать интерфейс Thread.UncaughtExceptionHandler.
2. После вызова uncaughtException нужно прервать нить, которая бросила исключение.
3. Затем, вывести в консоль стек исключений, начиная с самого вложенного исключения.
4. Сообщения должны выводиться в формате "exception class : exception message".
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        printException(e);
        /*List<String> list = new ArrayList<>();
        Throwable cause = e.getCause();
        list.add(e.toString());
        while (true) {
            list.add(cause.toString());
            cause = cause.getCause();
           if (cause == null)
               break;
        }
        for (int i = list.size()-1; i>=0;i--)
            System.out.println(list.get(i));
*/
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
        );
    }

    public void printException(Throwable e){
       try{
           printException(e.getCause());
       }
       catch (Exception ex){}

       String s = e.getClass().toString();
       s = s.substring(6);
        System.out.println(s+ ": " + e.getMessage());
    }
}
