package hw.tax;
import java.util.Scanner;
public class Main2 {

    public static int incomeValueFirst (int income) {
    int incTax = (income/100) * 6;
    if (incTax >= 0) {
        return incTax;
    }
    else {
        return 0;
    }
    }
    public static int incomeValueSecond (int income, int expenses) {
       int incExpTax = (income - expenses) * 15/100;
       if (incExpTax >= 0) {
           return incExpTax;
       }
       else {
           return 0;

       }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int income = 0;
        int expenses = 0;
        while (true) {
            System.out.println("Выберите операцию и введите ее номер:\n 1. Добавить новый доход\n " +
                    "2. Добавить новый расход\n 3.Выбрать систему налогообложения");
            String input = s.nextLine();
            if (input.equals("end")) {
                System.out.println("Программа завершена!");
            break;
        }

            int number = Integer.parseInt(input);
            switch(number) {
                case 1:
                    System.out.println("Введите сумму доходов:");
                    String incomeStr = s.nextLine();
                    int userIncome = Integer.parseInt(incomeStr);
                    income += userIncome;
                    System.out.println("Сумма доходов: " + income);
                    break;

                case 2:
                    System.out.println("Введите сумму расходов:");
                    String expensesStr = s.nextLine();
                    int userExpenses = Integer.parseInt(expensesStr);
                    expenses += userExpenses;
                    System.out.println("Сумма расходов: " + expenses);
                    break;

                case 3:
                   int firstTax = incomeValueFirst(income);
                   int secondTax = incomeValueSecond(income, expenses);
                    if (firstTax > secondTax) {
                        System.out.println("Мы советуем вам УСН доходы минус расходы.");
                        System.out.println("Ваш налог составит: " + secondTax);
                        System.out.println("Налог на другой системе составит" + firstTax);
                        System.out.println("Экономия: " + (firstTax - secondTax));
                        break;
                    }
                    else if (firstTax < secondTax) {
                        System.out.println("Мы советуем вам УСН доходы");
                        System.out.println("Ваш налог составит: " + firstTax);
                        System.out.println("Налог на другой системе составит " + secondTax);
                        System.out.println("Экономия: " + (secondTax - firstTax));
                        break;

                    }
                    else {
                        System.out.println("Вы можете выбрать любую систему налогообложения");
                        break;
                    }
                default:
                    System.out.println("Такой операции не существует.");

                    }

            }
            }
        }

