package ru.netology.service;

public class CustomService {
    public static final int WEIGHT_DUTY = 100;
    public static int calculateCustoms(int price, int weight) {
        int Duty = price / 100 + weight * WEIGHT_DUTY;
        return Duty;

    }
}
