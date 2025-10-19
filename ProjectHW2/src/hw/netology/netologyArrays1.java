package net.practicum;

import java.util.Scanner;
public class netologyArrays {
    public static void main(String[] args) {
        String[] products = {"Хлеб", "Сыр", "Кофе", "Молоко", "Лимон"};
        int[] prices = {55, 120, 140, 90, 40};

        for (int i = 0; i < products.length; i++) {

                System.out.println((i + 1) + "." + products[i] + " " + prices[i] + "руб/шт");
            }
        Scanner s = new Scanner(System.in);
        String[] userInput;
        int[] quantity = new int[products.length];
        int sumProducts = 0;
        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String input = s.nextLine();
            if(input.equals("end")) {
                System.out.println("Ваша корзина:");
                for (int j = 0; j < products.length; j++) {
                    sumProducts += prices[j]*quantity[j];
                    if (quantity[j] != 0) {
                        System.out.println(products[j] + " " + quantity[j] + " шт " + prices[j] + " руб/шт " + prices[j]*quantity[j] + " руб в сумме");
                    }
                }
                System.out.println("Итого " + sumProducts + " руб");
                break;
            }
            userInput = input.split(" ");
            int productNumber = Integer.parseInt(userInput[0]) - 1;
            int productCount = Integer.parseInt(userInput[1]);
            quantity[productNumber] = productCount;

        }
    }
}
