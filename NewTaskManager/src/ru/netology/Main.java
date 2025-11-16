package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        List<String> menu = new ArrayList<>();
        Tasks tasksLogic = new Tasks();
        Scanner scanner = new Scanner(System.in);
        Collections.addAll(menu, "Выход из программы", "Добавить дело", "Показать дела",
                "Удалить дело по номеру", "Удалить дело по названию", "Удалить дело по ключевому слову");
        while (true) {
            try {
                System.out.println("\nВыберите операцию:");
                for (int i = 0; i < menu.size(); i++) {
                    System.out.printf("%d. %s%n", i, menu.get(i));
                }
                int userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice == 0) {
                    System.out.println("Ваш выбор: 0");
                    System.out.println("Завершение программы");
                    return;
                } else if (userChoice == 1) {
                    System.out.println("Ваш выбор: 1");
                    tasksLogic.addTask(tasks, scanner);
                    tasksLogic.ListView(tasks);
                } else if (userChoice == 2) {
                    System.out.println("Ваш выбор: 2");
                    tasksLogic.ListView(tasks);
                } else if (userChoice == 3) {
                    System.out.println("Ваш выбор: 3");
                    tasksLogic.removeTaskWithIndex(tasks, scanner);
                    tasksLogic.ListView(tasks);
                } else if (userChoice == 4) {
                    System.out.println("Ваш выбор: 4");
                    tasksLogic.removeTaskWithName(tasks, scanner);
                    tasksLogic.ListView(tasks);
                } else if (userChoice == 5) {
                    System.out.println("Ваш выбор: 5");
                    tasksLogic.removeTaskWithWord(tasks, scanner);
                    tasksLogic.ListView(tasks);
                } else {
                    System.out.println("Некорректный номер операции");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректное значение");
            }
        }
    }
}
