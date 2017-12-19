package com.javarush.task.task39.task3909;


/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.print(isOneEditAway("alabk", "alabka"));

    }

    public static boolean isOneEditAway(String first, String second) {
        if ((first == null || first.length() < 2) && (second == null || second.length() < 2))
            return true;
        char[] maxStr = first.length() >= second.length() ? first.toCharArray() : second.toCharArray();
        char[] minStr = first.length() >= second.length() ? second.toCharArray() : first.toCharArray();
        //если длина различается больше чем на 1 символ
        if (maxStr.length - minStr.length > 1)
            return false;

        else if (maxStr.length == minStr.length) {
            int diff = 0;
            for (int i = 0; i < maxStr.length; i++) {
                if (maxStr[i] != minStr[i])
                    diff++;
                if (diff > 1)
                    return false;
            }
            return true;
        }
        //если строки отличаются на 1 по длине
        else if (maxStr.length - minStr.length == 1) {
            //найдем первый различающийся символ
            int i = 0;
            int diffIndex = 0;
            while (true){
                if (i == minStr.length){
                    diffIndex = i;
                    break;}
                if(maxStr[i] != minStr[i]){
                    diffIndex = i;
                    break;
                }
                i++;
            }
            String min = first.length() > second.length() ? second : first;
            String check = min.substring(0, diffIndex) + maxStr[i] + min.substring(diffIndex);
            return check.equals(first.length() > second.length() ? first : second);

        }

            return false;
    }
}
