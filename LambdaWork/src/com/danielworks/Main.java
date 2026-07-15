package com.danielworks;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
        int c;
        try {
            c = calc.divide.apply(a, b);  //деление на ноль приводит к ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: деление на ноль невозможно");
        c = 0;
        }
        calc.println.accept(c);
    }

}
