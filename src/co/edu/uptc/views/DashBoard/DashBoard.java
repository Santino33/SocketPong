package co.edu.uptc.views.DashBoard;

import java.awt.*;

import javax.swing.JFrame;
import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.utils.UtilsProperties;

public class DashBoard extends JFrame  {
    private MenuPanel menuPanel;
    protected GamePanel gamePanel;
    private UtilsProperties properties;

    public ContractPlay.Presenter presenter;


    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = presenter;
    }

    public ContractPlay.Presenter getPresenter() {
        return presenter;
    }

    public DashBoard(boolean isServer) {
        properties = new UtilsProperties();
        setLayout(new BorderLayout());
        initComponents(isServer);
    }

    private DashBoard getInstance() {
        return this;
    }

    public void initComponents(boolean isServer) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel = new MenuPanel(getInstance());
        add(menuPanel, BorderLayout.NORTH);
        gamePanel = new GamePanel(getInstance());
        add(gamePanel, BorderLayout.CENTER);
        this.addKeyListener(gamePanel);
        this.setLocationRelativeTo(null);

        if (isServer) {
            gamePanel.setBackground(Color.BLUE);
        }
    }


    public void begin() {
        gamePanel.threadPaint();
        setVisible(true);
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public UtilsProperties getProperties() {
        return properties;
    }

}
