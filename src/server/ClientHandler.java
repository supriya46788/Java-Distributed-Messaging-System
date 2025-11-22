package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Server server;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Enter your name:");
            clientName = reader.readLine();
            System.out.println("👤 User joined: " + clientName);

            server.broadcast("📢 " + clientName + " has joined the chat!", this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;

        try {
            while ((message = reader.readLine()) != null) {
                System.out.println("Message from " + clientName + ": " + message);
                server.broadcast("💬 " + clientName + ": " + message, this);
            }
        } catch (IOException e) {
            System.out.println("⚠ " + clientName + " disconnected.");
        } finally {
            closeConnection();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    private void closeConnection() {
        try {
            server.broadcast("❗ " + clientName + " has left the chat.", this);
            server.removeClient(this);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
