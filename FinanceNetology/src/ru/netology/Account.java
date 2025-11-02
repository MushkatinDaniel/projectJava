package ru.netology;

public abstract class Account {
    private long balance;
    private long creditLimit;

    public Account (long balance, long creditLimit) {
        this.balance = balance;
        this.creditLimit = -creditLimit;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean add(long amount) {
        boolean isSuccess = false;
        long oldBalance = getBalance();
        this.balance = balance + amount;
        if (balance == oldBalance + amount) {
            isSuccess = true;
            return isSuccess;
        }
        else {
            return isSuccess;
        }
    }

    public boolean pay(long amount) {
        boolean isSuccess = false;

        return isSuccess;
    }

    public boolean transfer(Account account, long amount) {
        boolean isSuccess = false;

        return isSuccess;
    }
}
