package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
Реализуй вспомогательныe методы в классе Solution, которые должны создавать соответствующую коллекцию и помещать туда переданные объекты.
Методы newArrayList, newHashSet параметризируй типом T.
Метод newHashMap параметризируй типами К(ключ) и V(значение). Аргументы метода newHashMap должны принимать списки, в которых содержатся наследники типов K и V.
Возвращаемые коллекции должны быть такого же типа, что и переданные в метод объекты.

Подсказка: в методе newHashMap нужно проверить чтобы списки ключей и значений совпадали по размерам, в противном случае кинь IllegalArgumentException.


*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList list = new ArrayList();
        for (Object element : elements)
            list.add(element);
        //напишите тут ваш код
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet set = new HashSet();
        for (Object element : elements)
            set.add(element);
        //напишите тут ваш код
        return set;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap map = new HashMap();
        if (keys.size() != values.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < values.size(); i++)
            map.put(keys.get(i), values.get(i));

        return map;
    }
}
