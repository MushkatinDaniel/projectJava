package ru.hamster;

public class hamsterLesson {
    /* public static void main(String[] args) {
         int targetAmount = 40000; // нужно накопить
         int day = 1; // переменная для подсчёта количества дней
         int dailyMoney = 1; // переменная, показывающая ежедневные поступления

         for (int money = dailyMoney; money<= targetAmount; money = money * 2) { // условие для цикла, переменную итерирования назовите money
             System.out.println("День " + day + ". На счету " + money);
             day++;
             // правила изменения дня и ежедневных поступлений
             }
         System.out.println("В варианте I на " + day + "-й день будет достигнута нужная сумма." );

         day = 1;
         dailyMoney = 100;
         // не забудьте обновить значения переменных day и dailyMoney
         for (int money = dailyMoney; money <= targetAmount; money = money + 100) { // условие для цикла. Переменную итерирования назовите money
             System.out.println("День " + day + ". На счету " + money);
             day++;
             // правила изменения дня и ежедневных поступлений
         }
         System.out.println("В варианте II на " + day + "-й день будет достигнута нужная сумма." );




     }
 }
*/
        public static void main(String[] args) {
            int targetAmount = 40000; // нужно накопить
            int day = 1; // переменная для подсчёта количества дней
            int dailyMoney = 1; // переменная, показывающая ежедневные поступления

            for (int money = dailyMoney; money <= targetAmount; money = dailyMoney + money * 2) { // условие для цикла, переменную итерирования назовите money
                System.out.println("День " + day + ". На счету " + money);
                day++;
                // правила изменения дня и ежедневных поступлений
            }
            System.out.println("В варианте I на " + day + "-й день будет достигнута нужная сумма.");

            day = 1;
            dailyMoney = 100;
            // не забудьте обновить значения переменных day и dailyMoney
            for (int money = dailyMoney; money <= targetAmount; money = dailyMoney + money) { // условие для цикла. Переменную итерирования назовите money
                System.out.println("День " + day + ". На счету " + money);
                day++;
                dailyMoney = dailyMoney + 100;
                // правила изменения дня и ежедневных поступлений
            }
            System.out.println("В варианте II на " + day + "-й день будет достигнута нужная сумма.");


        }
    }

