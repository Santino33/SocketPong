package co.edu.uptc.models.server;

import co.edu.uptc.models.GameStatus;
import co.edu.uptc.models.packages.InfoGame;

import java.net.Socket;

public class ClientConnection {
    private Socket clientSocket;
    private InfoGame clientInfoGame;

    public ClientConnection(Socket clientSocket, InfoGame clientInfoGame) {
        this.clientSocket = clientSocket;
        this.clientInfoGame = clientInfoGame;
    }

    public void sendInfo(){
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    new Thread(new ServerHandler(clientSocket, clientInfoGame)).start();
                }
            }
        }); sendThread.start();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public InfoGame getClientInfoGame() {
        return clientInfoGame;
    }
}
