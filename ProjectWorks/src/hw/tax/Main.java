package hw.tax;
import java.util.Scanner;
public class Main {

    public static double incomeTax(int income) {

        double bestSix = income * 0.06;

        return bestSix;

    }

    public static double incomeExpensesTax(int income, int expenses) {
        double incExpTax = (income - expenses) * 0.15;
        return incExpTax;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int incomeSum = 0;
        int expensesSum = 0;

        while (true) {
            System.out.println("Выберите операцию и введите ее номер:\n 1. Добавить новый доход\n " +
                    "2. Добавить новый расход\n 3.Выбрать систему налогооблажения");

            int number = s.nextInt();

            if (number == 1) {
                while (true) {
                    System.out.println("Введите доходы!:");
                    int income = s.nextInt();
                    incomeSum = incomeSum + income;
                    System.out.println(incomeSum);
                    break;
                }
            } else if (number == 2) {
                while (true) {
                    System.out.println("Введите раходы!:");
                    int expenses = s.nextInt();
                    expensesSum = expensesSum + expenses;
                    System.out.println(expensesSum);
                    break;
                }
            } else if (number == 3) {
                double firstTaxx = incomeTax(incomeSum);
                double secondTaxx = incomeExpensesTax(incomeSum, expensesSum);
                if (firstTaxx > secondTaxx) {
                    System.out.println("Мы советуем вам УСН доходы минус расходы.");
                    System.out.println("Ваш налог составит: " + secondTaxx);
                    System.out.println("Налог на другой системе составит" + firstTaxx);
                    System.out.println("Экономия: " + (firstTaxx - secondTaxx));
                    break;
                }
                else if (firstTaxx < secondTaxx) {
                    System.out.println("Мы советуем вам УСН доходы");
                    System.out.println("Ваш налог составит: " + firstTaxx);
                    System.out.println("Налог на другой системе составит " + secondTaxx);
                    System.out.println("Экономия: " + (secondTaxx - firstTaxx));
                    break;

                }
                else {
                    System.out.println("Вы можете выбрать любую систему налогообложения");
                    break;
                }

            }


            //  System.out.println("Выберите операцию и введите ее номер:\n 1. Добавить новый доход\n " +
            //          "2. Добавить новый расход\n 3.Выбрать систему налогооблажения");

/*
            double sas = incomeExpensesTax(int income; int expenses);
            double grass = incomeTax(int income2);

            if (sas<grass) {

            }
            else {


        }




       // String d = nextLine(); if (d.equals"end")
*/
//        }
//    }
        }
    }
}