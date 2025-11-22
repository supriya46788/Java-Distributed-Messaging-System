package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private BufferedReader consoleInput;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("✔ Connected to server!");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            consoleInput = new BufferedReader(new InputStreamReader(System.in));

            new Thread(new ReceiveHandler()).start();
            sendMessages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages() {
        try {
            String message;
            while ((message = consoleInput.readLine()) != null) {
                writer.println(message);
            }
        } catch (IOException e) {
            System.out.println("⚠ Cannot send message.");
        }
    }

    private class ReceiveHandler implements Runnable {
        @Override
        public void run() {
            String serverMessage;

            try {
                while ((serverMessage = reader.readLine()) != null) {
                    System.out.println(serverMessage);
                }
            } catch (IOException e) {
                System.out.println("⚠ Disconnected from server.");
            }
        }
    }

    public static void main(String[] args) {
        new Client("localhost", 5000);
    }
}
