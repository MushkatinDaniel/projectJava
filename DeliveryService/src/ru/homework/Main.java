package ru.homework;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Address moscow = new Address("Россия", "Москва");
        Address spb = new Address("Россия", "Санкт-Петербург");
        Address sochi = new Address("Россия", "Сочи");
        Address kazan = new Address("Россия", "Казань");
        Address novosib = new Address("Россия", "Новосибирск");
        Address chelyabinsk = new Address("Россия", "Челябинск");
        Address ny = new Address("США", "Нью-Йорк");
        Address seol = new Address("Южная Корея", "Сеул");
        Address beijing = new Address("Китай", "Пекин");
        Address cambridge = new Address("Англия", "Кембридж");
        Address la = new Address("США", "Лос-Анджелес");

        Map<Address, Integer> costPerAddress = new HashMap<>();
        costPerAddress.put(moscow, 100);
        costPerAddress.put(spb, 50);
        costPerAddress.put(sochi, 250);
        costPerAddress.put(kazan, 260);
        costPerAddress.put(novosib, 400);
        costPerAddress.put(chelyabinsk, 350);
        costPerAddress.put(ny, 5000);
        costPerAddress.put(seol, 4700);
        costPerAddress.put(beijing, 3200);
        costPerAddress.put(cambridge, 3900);
        costPerAddress.put(la, 5200);


        Set<String> uniqueAddress = new HashSet<>();

        int sum = 0;

        while (true) {
            System.out.println("Заполнение нового заказа.");
            System.out.println("Введите страну или end:");
            String userCountry = scanner.nextLine();
            if (userCountry.equals("end")) {
                System.out.println("Программа завершена.");
                break;
            }
            System.out.println("Введите город:");
            String userCity = scanner.nextLine();
            System.out.println("Введите вес (кг):");
            try {
                int weight = Integer.parseInt(scanner.nextLine());

                Address userAddress = new Address(userCountry, userCity);

                if (costPerAddress.containsKey(userAddress)) {
                    int pricePerKg = costPerAddress.get(userAddress);
                    int deliveryCost = pricePerKg * weight;
                    sum += deliveryCost;
                    uniqueAddress.add(userCountry);

                    System.out.println("Стоимость доставки составит: " + deliveryCost + " руб.");
                    System.out.println("Общая стоимость всех доставок: " + sum + " руб.");
                    System.out.println("Количество стран, в которые будут доставлены товары: " + uniqueAddress.size());
                } else {
                    System.out.println("Данный адрес не обслуживается.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректный вес");
            }
        }
    }
}
