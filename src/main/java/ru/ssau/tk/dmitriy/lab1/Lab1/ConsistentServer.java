package ru.ssau.tk.dmitriy.lab1.Lab1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsistentServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(11000)) {
            System.out.println("Сервер запущен. Ждем клиентов");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент. Сокет на стороне сервера открыт");
                BufferedReader inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("Потоки ввода/вывода созданы");
                outputSocket.write("Сервер переключился на вас и готов с вами работать\n");
                outputSocket.flush();
                int number;
                while ((number = inputSocket.read()) != 0) {
                    System.out.println("Получили число " + number);
                    outputSocket.write(isSimple(number));
                    outputSocket.flush();
                    System.out.println("Отправили ответ по " + number);
                }
                System.out.println("Пришел 0. Серверный сокет закрывается. Ждем следующего подключения\n");
                inputSocket.close();
                outputSocket.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String isSimple(int number) {
        if (number == 1) return "Число не является простым\n";
        int i = 2;
        while (i * i <= number) {
            if (number % i == 0) return "Число не является простым\n";
            i += 1;
        }
        return "Число - простое\n";
    }
}
