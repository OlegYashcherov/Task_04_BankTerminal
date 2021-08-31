package com.olegyashcherov.bank_terminal;

public class Person {
    private final String name;
    private int passwordHash;
    private int balance;

    public Person(String name, String pin, int balance) {
        this.name = name;
        setPin(pin);
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getPasswordHash() {
        return passwordHash;
    }

    public void setPin(String pin) {
        this.passwordHash = (pin+"соль").hashCode();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
