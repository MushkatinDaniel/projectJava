package ru.netology;

public class SimpleAccount extends Account {

    public SimpleAccount(long balance) {
        super(balance, 0);
    }

    @Override
    public boolean add(long amount) {
        boolean addSuccess = false;
        long oldBalance = getBalance();
        setBalance(getBalance() + amount);
        if ((oldBalance + amount) == getBalance()) {
            addSuccess = true;
        }
        return addSuccess;
    }

    @Override
    public boolean pay(long amount) {
        boolean paySuccess = false;
        if ((getBalance() - amount) >= 0) {
            setBalance(getBalance() - amount);
            paySuccess = true;
        }
        return paySuccess;
    }

    @Override
    public boolean transfer(Account account, long amount) {
        boolean transferSuccess = false;
        boolean add = false;
        boolean pay = pay(amount);
        if (pay) {
            add = account.add(amount);
        }
        if (add == false) {
            setBalance(getBalance() + amount);
        }
        if (pay && add) {
            transferSuccess = true;
        }
        return transferSuccess;
    }
}

