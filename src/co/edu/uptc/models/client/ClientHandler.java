package co.edu.uptc.models.client;

import co.edu.uptc.models.ManagerModel;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private ServerSocket clientServer;
    private Socket outputSocket;
    private ObjectOutputStream outputStream;
    private ManagerModel managerModel;
    private int outPort;

    public ClientHandler(int outPort) {
        this.outPort = outPort;
        try {
            managerModel = ManagerModel.getInstance();
            clientServer = new ServerSocket(outPort);
            outputSocket = clientServer.accept();
            outputStream = new ObjectOutputStream(outputSocket.getOutputStream());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){

        }
    }
}
