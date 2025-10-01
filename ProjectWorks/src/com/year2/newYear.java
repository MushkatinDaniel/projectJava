package com.year2;
import java.util.Scanner;

public class newYear {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
            /*int sum = 0;
            while (true){
                System.out.println("Введите год:");
            int userYear = s.nextInt();
                System.out.println("Введите количество дней:");
            int userDays = s.nextInt();

            int leapYear = 366;
            int noLeapYear = 365;

            if (userYear % 400 == 0 || userYear % 4 == 0) {
                int trueDays = 366;
                boolean

            }  */


//            public static int whatDays() {
//                int trueYear = userYear;
//                if (trueYear % 400 == 0 || trueYear % 4 == 0) {
//                    trueDays = 366;
//                } else if (trueYear % 100 == 0 || trueYear % 4 != 0) {
//                    trueDays = 365;
//                }
//
        int sum = 0;
        while (true) {

            System.out.println("Введите год:");
            int year = s.nextInt();
            System.out.println("Введите количество дней:");
            int days = s.nextInt();

            if (year % 400 == 0 || year % 4 == 0 && days == 366) {
                sum++;
            } else if (year % 100 == 0 || year % 4 != 0 && days == 365) {
                sum++;
            } else if (year % 400 == 0 || year % 4 == 0 && days != 366) {
                System.out.println("Неправильно! В этом году 366 дней!");
                System.out.println("Набрано очков: " + sum);
                break;
            } else if (year % 100 == 0 || year % 4 != 0 && days != 365) {
                System.out.println("Неправильно! В этом году 365 дней!");
                System.out.println("Набрано очков: " + sum);
                break;

            }
        }




//        System.out.println("Введите год в формате yyyy:");
//        int year = s.nextInt();
//        if (year % 400 == 0) {
//            System.out.println("Количество дней 366");
//        }
//        else if (year % 100 == 0) {
//            System.out.println("Количество дней 365");
//        }
//        else if (year % 4 == 0) {
//            System.out.println("Количество дней 366");
//        }
//        else {
//            System.out.println("Количество дней 365");
//        }

        }
    }


