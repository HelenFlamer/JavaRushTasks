package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций

В методы printFullyQualifiedNames и printValues приходит класс.
Если этот класс отмечен аннотацией PrepareMyTest,
необходимо вывести на экран fullyQualifiedNames и values в соответствующих методах и вернуть true.
 Если же аннотация PrepareMyTest отсутствует - вернуть false.

Для вывода на экран классов из массива value используй сокращенное имя (getSimpleName).
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (String s : prepareMyTest.fullyQualifiedNames())
                System.out.println(s);
            return true;        }
        else
        return false;
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (Class<?> s : prepareMyTest.value())
                System.out.println(s);
            return true;        }
        else
            return false;    }
}
