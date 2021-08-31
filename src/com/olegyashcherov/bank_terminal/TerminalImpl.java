package com.olegyashcherov.bank_terminal;

import com.olegyashcherov.bank_terminal.exception.AccountIsLockedException;

import java.util.Scanner;

public class TerminalImpl implements Terminal, ObserverUnLock {

    private final int CURRENCY_MULTIPLICITY = 100;
    private final int MAX_ATTEMPT_NUMBER = 3;

    private Person person;
    private Scanner scanner;
    private int attemptNumber;
    private volatile boolean accountIsBlocked;
    private boolean firstAfterBlocked = false;
//    private boolean pinIsValid;

    public TerminalImpl(Person person) {
        this.person = person;
    }

    public void init() {
        String currentStateConsole = "Здравствуйте, " + person.getName() + "!";
        System.out.println(currentStateConsole);
        scanner = new Scanner(System.in);

        try {
            attemptEnterPIN();
        } catch (AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void attemptEnterPIN() {

        boolean successAttempt = false;
        attemptNumber = 0;
        do {
            attemptNumber++;
            if (attemptNumber > MAX_ATTEMPT_NUMBER) {
                accountIsBlocked = true;
                AccountLockTimer accountLockTimer = new AccountLockTimer();
                accountLockTimer.setObserver(this);
                accountLockTimer.start();
                break;
            }

            String pin = enterPIN("\nВведите пин-код:\n", "", 0);
            if (isValidPIN(pin)) {
                successAttempt = true;
                break;
            } else {
                System.out.println("\nНеверный ПИН-код");
                if (getNumberAttemptRemainder() != 0) {
                    System.out.println("Количество попыток до блокировки аккаунта: " + getNumberAttemptRemainder() + "\n");
                }
            }
        } while (true);

        if (successAttempt) {
            toMoney();
        } else {

            while (accountIsBlocked) {

                long timeRemainderLocking = getTimeRemainderLocking();
                if (timeRemainderLocking > 0) {
                    scanner.nextLine();
                    System.out.println("До конца блокировки осталось " + getTimeRemainderLocking() + " секунд\n");
                }
            }
        }
    }

    private void toMoney() {
        System.out.println("Пин-код прошёл валидацию!");
    }


    private String enterPIN(String currentStateConsole, String currentPIN, int currentPinLength) {

        final int maxPinLength = 4;
        StringBuilder pinBuilder = new StringBuilder(currentPIN);
        String Separator = "----------------------------";

        StringBuilder stateConsoleBuilder = new StringBuilder(currentStateConsole);

        System.out.println(currentStateConsole);

        int pinLength = currentPinLength;

        do {
            if (firstAfterBlocked) {
                scanner = new Scanner(System.in);
                firstAfterBlocked = false;
            }
            String s = scanner.nextLine();
            if (isValidSymbol(s)) {
//                System.out.println("isValidSymbol + " + s);
                pinLength++;
                pinBuilder.append(s);
                stateConsoleBuilder.append(s);
                stateConsoleBuilder.append("\n");
                System.out.println(Separator);
                stateConsoleBuilder.append(Separator);
                stateConsoleBuilder.append("\n");
            } else {
                System.out.println("\nНеверный символ. Можно вводить только числа по одному символу\n");
                currentPIN = enterPIN(stateConsoleBuilder.toString(), pinBuilder.toString(), pinLength);
                break;
            }
            if (pinLength == maxPinLength) {
                return pinBuilder.toString();
            }
        } while (true);

        return currentPIN;
    }

    private long getTimeRemainderLocking() {
        return AccountLockTimer.LOCK_TIME / 1000 - (System.currentTimeMillis() - AccountLockTimer.beginLock) / 1000;
    }

    private int getNumberAttemptRemainder() {
        return MAX_ATTEMPT_NUMBER - attemptNumber;
    }

    @Override
    public boolean isValidSymbol(String s) {
        return s.matches("\\d");
    }

    @Override
    public boolean isValidPIN(String pin) {
        return PinValidator.isValidPIN(pin, person);
    }

    @Override
    public boolean isLockedAccount() {
        return false;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public boolean isValidSum(String sum) {
        return false;
    }

    @Override
    public void getMoney() {

    }

    @Override
    public void setMoney() {

    }

    @Override
    public void update() {
        accountIsBlocked = false;
        firstAfterBlocked = true;
        System.out.println("\nАккаунт разблокирован!\n");
        attemptEnterPIN();
    }

}
