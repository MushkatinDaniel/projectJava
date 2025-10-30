package ru.netology;

public class Sale extends Deal {

    public Sale (String productName, int productPrice) {
        super("Продажа " + productName + " на " + productPrice + " руб.", 0, productPrice);
    }
}
