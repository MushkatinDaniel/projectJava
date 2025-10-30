package ru.netology;

public class Expenditure extends Deal {

    public Expenditure (String productName, int productPrice) {
       super("Покупка " + productName + " на " + productPrice + " руб.", productPrice, 0);
    }
}
