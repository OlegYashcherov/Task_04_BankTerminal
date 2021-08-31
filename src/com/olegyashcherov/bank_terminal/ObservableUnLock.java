package com.olegyashcherov.bank_terminal;

public interface ObservableUnLock {
    void notifyObserver();

    void setObserver(ObserverUnLock o);

    void removeObserver();

}
