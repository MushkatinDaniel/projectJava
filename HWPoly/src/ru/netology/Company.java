package ru.netology;

public class Company {
    private String title;
    private int debit;
    private int credit;
    private TaxSystem taxSystem;

    public Company(String title, TaxSystem taxSystem) {
        this.title = title;
        this.taxSystem = taxSystem;
    }

    public void setTaxSystem(TaxSystem taxSystem) {
        this.taxSystem = taxSystem;
    }

    public void payTaxes() {
        int result = this.taxSystem.calcTaxFor(debit, credit);
        System.out.println("Компания " + title + " уплатила налог в размере: " + result + " руб.");
        debit = 0;
        credit = 0;
    }

    public void shiftMoney(int amount) {
        if (amount > 0) {
            debit += amount;
        } else if (amount < 0) {
            credit += Math.abs(amount);
        }

    }
    public int applyDeals(Deal[] deals) {
        for (Deal deal : deals) {
            debit += deal.getDebitChange();
            credit += deal.getCreditChange();
        }
        int result = debit - credit;
        payTaxes();
        if (result >= 0) {
            System.out.println("Доход: " + result + " руб.");
        }
        else {
            System.out.println("Убыток: " + result + " руб.");
        }
        return result;
    }
}

