package com.olegyashcherov.bank_terminal;

public interface Terminal {

    boolean isValidSymbol(String s);

    boolean isValidPIN(String pin);

    boolean isLockedAccount();

    int getBalance();

    boolean isValidSum(String sum);

    void getMoney();

    void setMoney();

}
