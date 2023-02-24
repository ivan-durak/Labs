package ru.ssau.tk.dmitriy.lab1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //Socket socket;
        //BufferedReader inputSocket;
        //BufferedWriter outputSocket;
        try (Socket socket = new Socket("localhost", 11000);
             BufferedReader inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            //socket = new Socket("localhost", 11000);
            //inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Сокет открыт");
            System.out.println("Потоки ввода/вывода созданы");
            System.out.print("""
                    Клиент принимает натуральное число, сервер отвечает, является ли оно простым или нет.
                    Чтобы закончить выполнение программы, введите 0
                    Давайте приступим,\s""");
            Scanner scanner = new Scanner(System.in);
            int number;
            String answer;
            while (true) {
                System.out.print("Введите число: ");
                while ((number = scanner.nextInt()) < 0) {
                    System.out.println("\nВведите число большее 0, чтобы продолжить; 0, если хотите закончить");
                }
                outputSocket.write(number);
                outputSocket.flush();
                if (number == 0) {
                    System.out.println("\nВы ввели 0, приложение закрывается. До скорых встреч)");
                    break;
                }
                answer = inputSocket.readLine();
                System.out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace(); //TODO:что-то другое делать возможно
        }
    }
}
