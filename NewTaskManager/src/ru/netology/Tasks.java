package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tasks {

    public void addTask(List<String> list, Scanner s) {
        System.out.println("Введите название задачи:");
        String task = s.nextLine();
        if (list.contains(task)) {
            System.out.println("Такая задача уже существует!");
            return;
        }
        list.add(task);
        System.out.println("Добавлено!");
    }

    public void ListView(List<String> list) {
        System.out.println("Ваш список дел:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i));
        }
    }

    public void removeTaskWithIndex(List<String> list, Scanner s) {
        System.out.println("Введите номер для удаления:");
        int userChoice = Integer.parseInt(s.nextLine());
        if (userChoice < 1 || userChoice > list.size()) {
            System.out.println("Задачи с номером " + userChoice + " не существует!");
            return;
        }
        list.remove(userChoice - 1);
        System.out.println("Удалено!");
    }

    public void removeTaskWithName(List<String> list, Scanner s) {
        System.out.println("Введите задачу для удаления:");
        String taskName = s.nextLine();
        boolean deleteTask = list.remove(taskName);
        if (deleteTask) {
            System.out.println("Удалено!");
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void removeTaskWithWord(List<String> list, Scanner s) {
        List<String> listCopy = new ArrayList<>(list);
        System.out.println("Введите слово: ");
        String word = s.nextLine();
        for (String task : listCopy) {
            if (task.contains(word)) {
                list.remove(task);
            }
        }
    }
}
