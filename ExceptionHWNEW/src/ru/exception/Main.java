package ru.exception;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            PasswordChecker checker = new PasswordChecker();
            System.out.println("Введите минимальную длину пароля: ");
            int minLength = s.nextInt();
            checker.setMinLength(minLength);
            System.out.println("Введите максимальное количество повторений символа подряд: ");
            int maxRepeat = s.nextInt();
            checker.setMaxRepeats(maxRepeat);

            while (true) {
                System.out.println("Введите новый пароль или end: ");
                String pass = s.nextLine();
                if (pass.equals("end")) {
                    System.out.println("Программа завершена");
                    break;
                }
                boolean isValid = checker.verify(pass);
                if (isValid) {
                    System.out.println("✓ Пароль подходит!");
                } else {
                    System.out.println("✗ Пароль не подходит!");
                }

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка :" + e.getMessage());
            System.out.println("Программа завершена из-за некорректных настроек");
        }

    }
}
