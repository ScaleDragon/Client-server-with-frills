package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static Server.service.Communication.questions;

public class Server {
    public static final int PORT = 9086;

    public static void main(String[] args) {
        String str;
        String username;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");

            try (Socket clientSocket = serverSocket.accept();  // ждем подключения
                 Scanner scanner = new Scanner(clientSocket.getInputStream());
                 PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println("New connection accepted");

                printWriter.println(questions("Write your name"));
                username = scanner.nextLine();

                while (username.isEmpty()) {
                    printWriter.println(questions("You have not entered your name"));
                    username = scanner.nextLine();
                }

                printWriter.println(questions("Are you child? answer: (yes/no)"));
                str = scanner.nextLine();
                while (str.isEmpty()) {
                    printWriter.println(questions("Please enter \"yes\" or \"no\""));
                    str = scanner.nextLine();
                }

                if (str.equalsIgnoreCase("yes")) {
                    printWriter.println(questions("What's your favorite game?"));
                    str = scanner.nextLine();
                    while (str.isEmpty()) {
                        printWriter.println(questions("Please enter your favorite game"));
                        str = scanner.nextLine();
                    }
                    printWriter.println(String.format("Welcome to the kids area, %s ! Let's play %s!",
                            username.toUpperCase(), str.toUpperCase()));
                } else {
                    printWriter.println(questions("What is your profession?"));
                    str = scanner.nextLine();
                    while (str.isEmpty()) {
                        printWriter.println(questions("Please enter your profession"));
                        str = scanner.nextLine();
                    }
                    printWriter.println(String.format("Welcome to the adult zone, %s! " +
                                                      "Have a good rest, I hope %s they know how to relax well ;)!",
                            username.toUpperCase(), str.toUpperCase()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
