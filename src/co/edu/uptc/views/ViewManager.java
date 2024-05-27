package co.edu.uptc.views;

import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.presenters.Presenter;
import co.edu.uptc.views.DashBoard.DashBoard;

public class ViewManager implements ContractPlay.View{
    private Presenter presenter;
    private DashBoard dashBoard;
    private WaitingRoom waitingRoom;
    private boolean isServer;

    public ViewManager(boolean isServer, Presenter presenter) {
        this.presenter = presenter;
        this.isServer = isServer;
        if (this.isServer) waitingRoom = new WaitingRoom();
        dashBoard = new DashBoard(this.isServer, presenter);
    }

    @Override
    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = (Presenter) presenter;
    }

    @Override
    public void begin() {
        dashBoard.setPresenter(this.presenter);
        dashBoard.begin();
    }

    @Override
    public void startWaitingRoom() {
        waitingRoom.start();
    }

    @Override
    public boolean isWaitingRoomEnded() {
        return waitingRoom.isEnded();
    }

    @Override
    public void setPlayersInWaitingRoom(int playersInWaitingRoom) {
        waitingRoom.updatePlayerCount(playersInWaitingRoom);
    }


}
