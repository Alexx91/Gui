package chat;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Socket> clientSockets = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer().run();
    }

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(5000);

            Socket clientSocket;

            while ((clientSocket = socket.accept()) != null)  {
                System.out.println("New client " + clientSocket.getInetAddress());
                clientSockets.add(clientSocket);
                Thread clientThread = new Thread(new clientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class clientHandler implements Runnable {
        Socket clientSocket;

        public clientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String message;
                while ((message = reader.readLine()) != null) {
                    sendToAll(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToAll(String message) {
        for (Socket socket : clientSockets) {
            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(message);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
