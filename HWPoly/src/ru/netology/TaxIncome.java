package ru.netology;

public class TaxIncome extends TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        int result = (int)(debit * 0.06);
        if (result < 0) {
            return 0;
        }
        else {
            return result;
        }
    }
}
