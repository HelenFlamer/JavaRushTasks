package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код

        String res = number + " =";
        String s = "";
        while (number > 0) {
            int ost = number % 3;
            if (ost == 0) {
                s += "0";
                number = number / 3;
            }
            if (ost == 1){
                s += "+";
                number = number / 3;
            }
            if (ost == 2){
                s += "-";
                number = 1 + (number / 3);
            }
        }

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '-')
                res += " - "  + (int) Math.pow(3, i);
            if (ch == '+')
                res += " + " + (int) Math.pow(3, i);

        }
        System.out.println(res);

    }
}