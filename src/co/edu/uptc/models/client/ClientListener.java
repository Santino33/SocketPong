package co.edu.uptc.models.client;

import co.edu.uptc.models.packages.InfoGame;
import co.edu.uptc.models.ManagerModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientListener implements Runnable{
    private ManagerModel managerModel;
    private InfoGame infoGame;
    private ObjectInputStream inputStream;
    private Socket clientSocket;

    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.managerModel = ManagerModel.getInstance();
        try {
            inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try{
            while (true){
                infoGame = (InfoGame) inputStream.readObject();
                printInfo();
                managerModel.setInfoGame(infoGame);
                Thread.sleep(30);
            }
        }catch (ClassNotFoundException | IOException | InterruptedException e){
            e.printStackTrace();
        }

    }

    public void printInfo(){
        System.out.println(infoGame.getBallPojo().getX() + " ; " + infoGame.getBallPojo().getY());
    }
}
