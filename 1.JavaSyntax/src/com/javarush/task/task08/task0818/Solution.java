package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String , Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++)
            map.put("Name"+i, (int) (Math.random()*1000));
        return map;
        //напишите тут ваш код
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        HashMap<String , Integer> cloneMap = (HashMap<String, Integer>) map.clone();
        for (Map.Entry<String, Integer> m : cloneMap.entrySet()){
            if (m.getValue() < 500)
                map.remove(m.getKey(), m.getValue());
        }
        //напишите тут ваш код
    }

    public static void main(String[] args) {

    }
}