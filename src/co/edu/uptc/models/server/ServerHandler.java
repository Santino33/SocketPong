package co.edu.uptc.models.server;

import co.edu.uptc.models.packages.InfoGame;
import co.edu.uptc.models.ManagerModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable{
    private Socket serverSocket;
    private InfoGame infoGame;
    private ManagerModel managerModel;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServerHandler(Socket serverSocket, InfoGame infoGame) {
        this.serverSocket = serverSocket;
        this.infoGame = infoGame;
        this.managerModel = ManagerModel.getInstance();
        try {
            out = new ObjectOutputStream(this.serverSocket.getOutputStream());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while(true){
                updateInfoGame();
                printInfoGame();
                out.writeObject(infoGame);
                out.flush();
                out.reset();
                Thread.sleep(30);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updateInfoGame(){
        infoGame = managerModel.getInfoGame();
    }

    private void printInfoGame(){
        System.out.println(infoGame.getBallPojo().getX() + " ; " + infoGame.getBallPojo().getY());
    }
}
