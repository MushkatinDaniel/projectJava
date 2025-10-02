package net.practicum;

public class newPractic {
        public static void main(String[] args) {

            int age = 65;
            String dayOfWeek = "SUNDAY";
            String monday = "MONDAY";
            String sunday = "SUNDAY";

            int ticketPrice = 0;
            if (age<6) {
                ticketPrice = 0;
            }
            else
            if (age >= 65) {
                ticketPrice = 5;
                if (dayOfWeek.equals(sunday)) {
                    ticketPrice = 3;
                }
            }
            else if (age>=6 || age < 65) {
                if (dayOfWeek.equals(monday)) {
                    ticketPrice = 8;
                }
                else {
                    ticketPrice = 10;
                }
            }

            System.out.println("Цена билета составляет: " + ticketPrice);
        }
    }

