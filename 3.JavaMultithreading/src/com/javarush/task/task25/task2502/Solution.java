package com.javarush.task.task25.task2502;

import java.io.IOException;
import java.util.*;

/* 
Машину на СТО не повезем!
Инициализируй поле wheels используя данные из loadWheelNamesFromDB.
Выкинь исключение в случае некорректных данных.

Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            wheels = new ArrayList<>();
            String[] mass = loadWheelNamesFromDB();
            if (mass.length != 4){
                throw new IllegalArgumentException();
            }
            for (int i = 0; i<mass.length; i++){
                Wheel wheel = Wheel.valueOf(mass[i]);
                wheels.add(wheel);
            }


        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT" };
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        System.out.println(car.wheels);

    }
}
