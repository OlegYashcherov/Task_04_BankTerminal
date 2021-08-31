package com.olegyashcherov.bank_terminal;

public final class PinValidator {
    private PinValidator() {
    }

    public static boolean isValidPIN(String pin, Person person) {
        return (pin+"соль").hashCode() == person.getPasswordHash();
    }
}
