package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String localhost = "netology.homework";
        try (Socket clientSocket = new Socket()) {
            clientSocket.connect(new InetSocketAddress(localhost, Server.PORT));
            PrintWriter writer = new
                    PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(new InputStreamReader(clientSocket.getInputStream()));

            while (in.hasNextLine()) {
                if (in.hasNextLine()) {
                    Scanner scanner = new Scanner(System.in);
                    String answer;
                    System.out.println(in.nextLine());
                    answer = scanner.nextLine();
                    writer.println(answer);
                } else {
                    break;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
