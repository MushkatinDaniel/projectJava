package net.year3;
import java.util.Scanner;

public class leapYear {

    public static int getTrueDays (int userYear) {
        if (userYear % 4 == 0 && userYear % 100 != 0 || userYear % 400 == 0) {
            return 366;
        }
        else {
            return 365;
        }
        }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sum = 0;
        while(true) {
        System.out.println("Введите год:");
        int userYear = s.nextInt();
        System.out.println("Введите количество дней:");
        int userDays = s.nextInt();

        int trueDays = getTrueDays(userYear);
        if (userDays == trueDays) {
            sum++;
        }
        else {
            System.out.println("Неправильно! В этом году " + trueDays + " дней!");
            System.out.println("Набрано очков: " + sum);
            break;
        }



    }
    }
}
