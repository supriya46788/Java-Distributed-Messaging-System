package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private ServerSocket serverSocket;
    private Set<ClientHandler> clients = new HashSet<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("🚀 Server started on port " + port);
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("✅ New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);

                new Thread(clientHandler).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("❌ Client disconnected. Active clients: " + clients.size());
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
