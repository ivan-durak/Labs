package ru.ssau.tk.dmitriy.lab1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ParallelServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(11000)) {
            System.out.println("Сервер запущен. Ждем подключений");
            int countOfThreads = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент.\nСокет на стороне сервера открыт");
                new ServerChildThread(socket, countOfThreads).start();
                countOfThreads++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO:возможно по-другому обрабатывать
        }
    }
}
