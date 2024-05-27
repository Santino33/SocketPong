package co.edu.uptc.models.server;

import co.edu.uptc.models.GameStatus;
import co.edu.uptc.models.ManagerModel;
import co.edu.uptc.models.packages.InfoGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ManagerModel managerModel;
    private ArrayList<ClientConnection> clientConnections;

    private final int port = 5000;

    public Server() {
        try {
            managerModel = ManagerModel.getInstance();
            clientConnections = new ArrayList<ClientConnection>();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        waitForPlayers();
        sendInfoToClients();
    }

    private void waitForPlayers(){
        Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(managerModel.getGameStatus() == GameStatus.WAITING){
                    try {
                        Socket linkClient = serverSocket.accept();
                        ClientConnection connection = new ClientConnection(linkClient, configureClientInfoGame());
                        clientConnections.add(connection);
                        managerModel.getTable().duplicateSize();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }); waitThread.start();
    }

    private void sendInfoToClients(){
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean gameRunning = false;
                while(!gameRunning){
                    if (managerModel.getGameStatus() == GameStatus.RUNNING){
                        for (ClientConnection clientConnection: clientConnections){
                            clientConnection.sendInfo();
                            System.out.println("InfoGame enviado a cliente");
                        }
                        gameRunning = true;
                    }
                }
            }
        }); sendThread.start();
    }

    private InfoGame configureClientInfoGame(){
        InfoGame infoGame = managerModel.getInfoGame();
        int clientPosition = clientConnections.size();
        if (clientPosition == 0){
            infoGame.setPresentPadle1(true);
        } else if (clientPosition == 1) {
            infoGame.setPresentPadle2(true);
        }
        return infoGame;
    }

    public int getNumberClients(){
        return clientConnections.size();
    }
}
