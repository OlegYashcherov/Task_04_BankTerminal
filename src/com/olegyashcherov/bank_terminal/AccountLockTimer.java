package com.olegyashcherov.bank_terminal;

public class AccountLockTimer extends Thread implements ObservableUnLock {

    public static final long LOCK_TIME = 10000;

//    public static volatile boolean isLockingWorks;
    public static volatile long beginLock;

    private ObserverUnLock observer;

    @Override
    public void run() {
        System.out.println("\nАккаунт заблокирован на 10 секунд!\n");
//        observer.setLock(true);
        beginLock = System.currentTimeMillis();
        try {
            Thread.sleep(LOCK_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        observer.setLock(false);
//        isLockingWorks = false;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update();
    }

    @Override
    public void setObserver(ObserverUnLock o) {
        observer = o;
    }

    @Override
    public void removeObserver() {
        observer = null;
    }

}
