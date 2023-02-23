package ru.ssau.tk.dmitriy.lab1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsistentServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(11000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Серверный сокет открыт");
                BufferedReader inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("Потоки ввода/вывода созданы");
                int number;
                while ((number = inputSocket.read()) != 0) {

                    int[][] decomposition = decomposeTheNumber(number);
                    //TODO:преобразовать в строку наверное
                    String s = "";
                    outputSocket.write(s);
                }
                System.out.println("Пришел 0. Серверный сокет закрывается. Ждем следующего подключения");
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO:что-то другое делать возможно
        }
    }

    private static int[][] decomposeTheNumber(int number) {
        return new int[3][4];
    }
}
