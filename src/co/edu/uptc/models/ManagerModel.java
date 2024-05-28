package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.models.packages.InfoGame;
import co.edu.uptc.models.client.Client;
import co.edu.uptc.models.server.Server;
import co.edu.uptc.pojos.ElementPojo;
import co.edu.uptc.pojos.Table;
import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.utils.Utils;
import co.edu.uptc.utils.UtilsProperties;

public class ManagerModel implements ContractPlay.Model {
    private static ManagerModel instance;

    private ContractPlay.Presenter presenter;
    private UtilsProperties properties;

    private ArrayList<ElementPojo> pojos;
    private BallModel ball;
    private PadleModel padle1;
    private PadleModel padle2;
    private Table table;
    private GameStatus gameStatus;
    private Server server;
    private Client client;
    private int players;
    //This array contains the booleans that indicate if the view has to print elements. Ball[0], Padle1[1], Padle2[2]
    private boolean[] isPresentControl;

    private ManagerModel(){
        players = 0;
        table = new Table();
        gameStatus = GameStatus.WAITING;
        properties = new UtilsProperties();
        pojos = new ArrayList<>();
        createElement();
        Collision();
        instance = this;
        isPresentControl = new boolean[3];
    }

    public static synchronized ManagerModel getInstance() {
        if (instance == null) {
            instance = new ManagerModel();
        }
        return instance;
    }

    public void createClient(){
        client = new Client();
        client.start();
    }

    public void createServer(){
        server = new Server();
        server.start();
    }

    @Override
    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = presenter;
    }

    public void Collision() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Utils.sleep(80);
                    checkCollision();
                }
            }
        });
        thread.start();
    }

    public void checkCollision() {
        if (ball.intersects(padle1)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            System.out.println("choque paleta 1");
        }

        if (ball.intersects(padle2)) {
            ball.setYVelocity(Math.abs(ball.getXVelocity()));
            ball.setXDirection(-ball.getXVelocity());
            System.out.println("choque paleta 2");
        }
    }

    public void createElement() {
        ball = new BallModel(750, 300, 40, 40);
        padle1 = new PadleModel(20, 100, 50, 80);
        padle2 = new PadleModel(1450, 100, 50, 100);
    }

    @Override
    public void start() {
        ball.startElement();
    }

    @Override
    public void stop() {
        ball.stopElement();
    }

    @Override
    public ArrayList<ElementPojo> getElementsPojo() {
        pojos.clear();
        pojos.add(ball.getBallPojo());
        pojos.add(padle1.getPadlePojo());
        pojos.add(padle2.getPadlePojo());
        return pojos;
    }

    @Override
    public PadleModel getPadle1() {
        return padle1;
    }

    @Override
    public PadleModel getPadle2() {
        return padle2;
    }

    @Override
    public int getNumberPlayers() {
        return server.getNumberClients();
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public Table getTable() {
        return this.table;
    }

    public BallModel getBall() {
        return ball;
    }

    public InfoGame getInfoGame(){
        return new InfoGame(ball.getballPojo(),
                padle1.getPadlePojo(),
                padle2.getPadlePojo(), table);
    }

    public void setInfoGame(InfoGame info){
        this.ball.setBallPojo(info.getBallPojo());
        this.padle1.setPadlePojo(info.getPadle1Pojo());
        this.padle2.setPadlePojo(info.getPadle2Pojo());
        this.isPresentControl[0] = info.isPresentBall();
        this.isPresentControl[1] = info.isPresentPadle1();
        this.isPresentControl[2] = info.isPresentPadle2();
        this.table.setHeight(info.getTableSize().getHeight());
        this.table.setWidth(info.getTableSize().getWidth());
    }
}
