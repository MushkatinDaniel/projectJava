package ru.year;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите год в формате yyyy:");
        int x = s.nextInt();
        if (x % 400 ==0 || x % 4 ==0 && x % 100 != 0) {
            System.out.println("Количество дней 366");
        }
        else {
            System.out.println("Количество дней 365");
        }
    }
}
