package ru.practicum2;
import java.util.Scanner;
public class integNew {
    public static void main(String[] args) {

        double rateUSD = 81.9;
        double rateEUR = 87.7;
        double rateCNY = 11.49;
        /*
        объявите переменные и сразу присвойте им значение:
        - rateUSD (курс доллара)
        - rateEUR (курс евро)
        - rateCNY (курс юаня)
         */

           /* System.out.println("Введите сумму рублей для конвертации:");
           // double roubles = NumberReader.getDouble(); // метод для ввода дробных чисел

            System.out.println("Введите номер валюты, в какую перевести рубли:");
            System.out.println("1 – доллары;");
            System.out.println("2 – евро;");
            System.out.println("3 – юани;");

            // сохраните результат команды
           // int command = NumberReader.getInteger(); // метод для ввода целых чисел

            // проверьте, введена ли команда под номером 1
            if (command == 1) {
                double RUBtoUSD = roubles/rateUSD; // сконвертируйте рубли в доллары
                if (RUBtoUSD < 0) { // проверьте результат - если строго меньше нуля, выводим ошибку
                    System.out.println("Ошибка: некорректные значения.");
                } else {
                    // выведите корректное сообщение на экран
                    System.out.println("Было введено " + roubles + ", в долларах это " + RUBtoUSD);
                }
                // проверьте, введена ли команда под номером 2
            } else if (command == 2) {
                double RUBtoEUR = roubles/rateEUR; // сконвертируйте рубли в евро
                if (RUBtoEUR < 0) { // сравните результат с нулём
                    System.out.println("Ошибка: некорректные значения."); // выведите сообщение об ошибке
                } else {
                    System.out.println("Было введено " + roubles + ", в евро это " + RUBtoEUR);
                }// выведите сообщение о результате на экран
            } else if (command == 3) {
                double RUBtoCNY = roubles/rateCNY;
                if (RUBtoCNY < 0) {
                    System.out.println("Ошибка: некорректные значения.");

                }
                else {
                    System.out.println("Было введено " + roubles + ", в юанях это " + RUBtoCNY);

                }
            }// добавьте блок if-else для юаней – команды под номером 3
            else {
                System.out.println("Такой команды нет.");
            }
            System.out.println("Работа с программой завершена.");
        }/*
    }

            */

    }
}