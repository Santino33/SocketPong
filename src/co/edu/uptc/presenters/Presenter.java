package co.edu.uptc.presenters;

import java.util.ArrayList;

import co.edu.uptc.models.GameStatus;
import co.edu.uptc.models.ManagerModel;
import co.edu.uptc.pojos.ElementPojo;
import co.edu.uptc.presenters.ContractPlay.Model;
import co.edu.uptc.presenters.ContractPlay.View;
import co.edu.uptc.views.DashBoard.DashBoard;
import co.edu.uptc.views.ViewManager;

public class Presenter implements ContractPlay.Presenter {

    private ContractPlay.View view;
    private ContractPlay.Model model;

    public void makeMVP(boolean isServer) {
        ManagerModel managerModel = ManagerModel.getInstance();

        ViewManager viewManager = new ViewManager(isServer, this);
        setView(viewManager);

        managerModel.setPresenter(this);
        setModel(managerModel);

        if (isServer) {
            waitForPlayers();
        }
        else managerModel.createClient();


    }


    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void beginServer() {
        makeMVP(true);
        view.begin();
    }

    @Override
    public void beginClient(){
        makeMVP(false);
        view.begin();
    }



    @Override
    public ArrayList<ElementPojo> getElementPojo() {
        return model.getElementsPojo();
    }

    @Override
    public void start() {
        model.start();
    }

    @Override
    public void stop() {
        model.stop();
    }

    private void waitForPlayers(){
        model.setGameStatus(GameStatus.WAITING);
        view.startWaitingRoom();
        model.createServer();
        while(model.getGameStatus() == GameStatus.WAITING){
            try {
                view.setPlayersInWaitingRoom(model.getNumberPlayers());
                if (view.isWaitingRoomEnded()){
                    model.setGameStatus(GameStatus.RUNNING);
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
