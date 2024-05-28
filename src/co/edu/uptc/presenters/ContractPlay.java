package co.edu.uptc.presenters;

import java.util.ArrayList;

import co.edu.uptc.models.GameStatus;
import co.edu.uptc.models.PadleModel;
import co.edu.uptc.pojos.Table;
import co.edu.uptc.pojos.ElementPojo;

public class ContractPlay {
    public interface Model {
        public void setPresenter(Presenter presenter);

        public void start();

        public void stop();

        public ArrayList<ElementPojo> getElementsPojo();

        public PadleModel getPadle1();

        public PadleModel getPadle2();
        public int getNumberPlayers();
        public void createServer();
        public GameStatus getGameStatus();
        public void setGameStatus(GameStatus gameStatus);
        public Table getTable();
    }

    public interface View {
        public void setPresenter(Presenter presenter);

        public void begin();
        public void startWaitingRoom();
        public boolean isWaitingRoomEnded();
        public void setPlayersInWaitingRoom(int playersInWaitingRoom);
    }

    public interface Presenter {

        public void setModel(Model model);

        public void setView(View view);

        public void beginServer();
        public void beginClient();

        public void start();

        public void stop();

        public Model getModel();

        public ArrayList<ElementPojo> getElementPojo();

        public Table getTable();

    }
}