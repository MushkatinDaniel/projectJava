package ru.netology;

public class CreditAccount extends Account {

    public CreditAccount(long creditLimit) {
        super(0, creditLimit);
    }


    @Override
    public boolean add(long amount) {
        boolean addSuccess = false;
        if ((getBalance() + amount) <= 0) {
            this.setBalance(getBalance() + amount);
            addSuccess = true;
        }
        return addSuccess;
    }

    @Override
    public boolean pay(long amount) {
        boolean paySuccess = false;
        if ((getBalance() - amount) >= this.getCreditLimit()) {
            setBalance(getBalance() - amount);
            paySuccess = true;
        }
        return paySuccess;
    }

    @Override
    public boolean transfer(Account account, long amount) {
        boolean transferSuccess = false;
        boolean add = true;
        boolean pay = pay(amount);
        if (pay) {
            add = account.add(amount);
        }
        if (!add) {
            setBalance(getBalance() + amount);
        }
        if (pay && add ) {
            transferSuccess = true;
        }
        return transferSuccess;
    }
}
