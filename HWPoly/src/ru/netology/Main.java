package ru.netology;

public class Main {
    public static void main(String[] args) {

        TaxSystem mainTaxSystem = new TaxSystem();
        Company company = new Company("Software company", mainTaxSystem);
        TaxSystem income = new TaxIncome();
        TaxSystem incomeExp = new TaxIncomeExp();

        company.setTaxSystem(income);
        company.shiftMoney(100);
        company.shiftMoney(-20);
        company.payTaxes();

        company.setTaxSystem(incomeExp);
        company.shiftMoney(400);
        company.shiftMoney(-95);
        company.payTaxes();

        Deal[] deals = new Deal[5];
        deals[0] = new Expenditure("Видеокарты", 30500);
        deals[1] = new Sale("Услуга", 9800);
        deals[2] = new Expenditure("Ноутбук", 60000);
        deals[3] = new Sale("Лицензия на ПО", 20000);
        deals[4] = new Sale("Сервис", 90000);

        for (int i = 0; i< deals.length; i++) {
            System.out.println("  " + (i + 1) + ". " + deals[i].getComment());
        }

        company.applyDeals(deals);


    }
}
