package ru.netology;

public class Main {
    public static void main(String[] args) {
        Account credit = new CreditAccount(1000);
        System.out.println(credit.getCreditLimit());
        Account simple = new SimpleAccount(2000);
        System.out.println(simple.getBalance());
        System.out.println(credit.getBalance());
        credit.transfer(simple, 400);
        System.out.println(credit.getBalance());
        System.out.println(simple.getBalance());
        credit.transfer(simple, 600);
        System.out.println(credit.getBalance());
        System.out.println(simple.getBalance());


        SmartLogger logger = new SmartLogger();
        logger.log("Пользователь не зарегистрирован");
        logger.log("неизвестная ошибка, error");
        }


    }

