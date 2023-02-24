package ru.ssau.tk.dmitriy.lab1;

import java.io.*;
import java.net.Socket;

public class ServerChildThread extends Thread {
    private final Socket socket;
    private final BufferedReader inputSocket;
    private final BufferedWriter outputSocket;
    private final int ordinalNumber;

    public ServerChildThread(Socket socket, int ordinalNumber) {
        this.socket = socket;
        this.ordinalNumber = ordinalNumber;
        System.out.println("Дочерний поток " + ordinalNumber + " создается");
        try {
            this.inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Потоки ввода/вывода потока " + ordinalNumber + " созданы");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Поток " + ordinalNumber + " начал выполнение");
        try {
            outputSocket.write("Сервер создал отдельный поток для предоставления своего функционала\n");
            outputSocket.flush();
            int number;
            while ((number = inputSocket.read()) != 0) {
                System.out.println("Поток " + ordinalNumber + " получил число " + number);
                outputSocket.write(ConsistentServer.isSimple(number));
                outputSocket.flush();
                System.out.println("Поток " + ordinalNumber + " отправил ответ " + number);
            }
            socket.close();
            System.out.println("Дочерний поток " + ordinalNumber + " закрывается");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
