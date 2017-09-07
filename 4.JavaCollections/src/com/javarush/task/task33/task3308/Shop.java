package com.javarush.task.task33.task3308;


import java.util.List;

public class Shop {
    public int count;
    public Goods goods;
    public double profit;
    public String[] secretData;

    private static class Goods{
        private List<String> names;

    }
}
