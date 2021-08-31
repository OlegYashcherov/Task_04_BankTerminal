package com.olegyashcherov.bank_terminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalServer {
    private static TerminalServer instance;
    private List<Person> personList;
    private static final Object LOCK = new Object();

    public TerminalServer() {
        personList = new ArrayList<>();
        personList.add(new Person("Андрей","1111", 10000));
        personList.add(new Person("Виктория","2222", 52523));
        personList.add(new Person("Роман","1111", 19750));
        personList.add(new Person("Юлия","2222", 94900));
    }

    public int getPersonListSize() {
        return personList.size();
    }

    public Person getPersonById(int id) {
        return personList.get(id);
    }

    public static TerminalServer getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new TerminalServer();
            }
        }
        return instance;
    }
}
