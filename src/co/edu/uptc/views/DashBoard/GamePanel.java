package co.edu.uptc.views.DashBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import co.edu.uptc.pojos.ElementPojo;
import co.edu.uptc.utils.Utils;

public class GamePanel extends JPanel implements KeyListener {
    private DashBoard dashBoard;
    private ArrayList<ElementPojo> elementsPojo;

    public GamePanel(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        elementsPojo = new ArrayList<>();
    }

    public void initComponents() {
        setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawOval(elementsPojo.get(0).getX(), elementsPojo.get(0).getY(), elementsPojo.get(0).getWidth(),
                elementsPojo.get(0).getHeight());

        ElementPojo padle1 = elementsPojo.get(1);
        g.drawRect(padle1.getX(), padle1.getY(), padle1.getWidth(), padle1.getHeight());

        ElementPojo padle2 = elementsPojo.get(2);
        g.drawRect(padle2.getX(), padle2.getY(), padle2.getWidth(), padle2.getHeight());

    }

    public void threadPaint() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Utils.sleep(dashBoard.getProperties().getSleepGame());
                    elementsPojo = dashBoard.presenter.getElementPojo();
                    repaint();
                }
            }
        });
        thread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                dashBoard.presenter.getModel().getPadle1().Down();
                break;
            case KeyEvent.VK_Q:
                dashBoard.presenter.getModel().getPadle1().Up();
                break;
            case KeyEvent.VK_O:
                dashBoard.presenter.getModel().getPadle2().Up();
                break;
            case KeyEvent.VK_L:
                dashBoard.presenter.getModel().getPadle2().Down();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setElementsPojo(ArrayList<ElementPojo> elementsPojo) {
        this.elementsPojo = elementsPojo;
    }
}
