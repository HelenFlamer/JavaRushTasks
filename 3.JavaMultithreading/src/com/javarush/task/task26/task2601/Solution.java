package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        sort(new Integer[]{1, 2, 3, 4});
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer middle;
        Arrays.sort(array);
        if (array.length % 2 == 1) {
            middle = array[(array.length - 1) / 2];
        } else {
            middle = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        }
        Comparator<Integer> compareByMedian = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double value = Math.abs(o1 - middle) - Math.abs(o2 - middle);
                if (value == 0)
                    value = o1 - o2;
                return (int) value;
            }
        };
        Arrays.sort(array, compareByMedian);
        return array;

    }


}
