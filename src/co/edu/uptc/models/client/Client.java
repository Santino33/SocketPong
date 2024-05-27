package co.edu.uptc.models.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ServerSocket clientServer;
    private final int inPort = 5000;
    private final int outPort = 9000;

    public Client() {
        try {
            clientServer = new ServerSocket(outPort);
            clientSocket = new Socket("192.168.1.77", inPort);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void start(){
        new Thread(new ClientListener(this.clientSocket)).start();
    }
}
