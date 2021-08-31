package com.olegyashcherov.bank_terminal;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Пользователь прикладывает карту, сервер возвращает владельца карты.
        // Инициализируется работа терминала с конкретным человеком
        // (случайный пользователь из базы сервера).
        // Сервер реализован в виде синглтона и защищен для работы из разных потоков.

        TerminalServer terminalServer = TerminalServer.getInstance();

        Random r = new Random();
        int randomPersonId = r.nextInt(terminalServer.getPersonListSize());

        new TerminalImpl(terminalServer.getPersonById(randomPersonId)).init();
    }
}
