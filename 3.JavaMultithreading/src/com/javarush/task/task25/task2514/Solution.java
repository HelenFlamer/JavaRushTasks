package com.javarush.task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
Обеспечь переуступку кванта времени (переход хода для текущей нити) для последовательных выводов текста в консоль.


Требования:
1. Класс Solution должен содержать вложенный класс YieldRunnable, который реализует интерфейс Runnable.
2. Класс YieldRunnable должен иметь поле с индексом, которое инициализируется через конструктор.
3. Метод run() должен выводить в консоль сообщения с текущим индексом о начале и конце работы метода.
4. В правильном месте должен быть вызван Thread.yield.
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();

            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
    }
}
