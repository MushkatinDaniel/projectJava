package ru.netology;

public class TaxIncomeExp extends TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        int result = (int)((debit - credit) * 0.15);
        if (result < 0) {
            return 0;
        }
        else {
            return result;
        }
    }
}
