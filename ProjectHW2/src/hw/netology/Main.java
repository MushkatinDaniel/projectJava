package hw.netology;

import ru.netology.service.CustomService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Здравствуйте!");
        System.out.print("Введите цену товара (в руб.):");
        int price = s.nextInt();

        System.out.print("Введите вес товара (в кг.):");
        int weight = s.nextInt();

        int fullDuty = CustomService.calculateCustoms(price, weight);

        System.out.println("Размер пошлины составит: " + fullDuty + " рублей.");

    }
}
