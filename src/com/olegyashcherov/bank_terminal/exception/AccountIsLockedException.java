package com.olegyashcherov.bank_terminal.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException() {
        super();
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

    public AccountIsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsLockedException(Throwable cause) {
        super(cause);
    }

    protected AccountIsLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
