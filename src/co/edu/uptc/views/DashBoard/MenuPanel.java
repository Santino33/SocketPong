package co.edu.uptc.views.DashBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuPanel extends javax.swing.JPanel {
    private JButton btnStart;
    private JButton btnStop;
    private DashBoard dashBoard;

    public MenuPanel(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    private void initComponents() {
        setBounds(1, 1, dashBoard.getProperties().getWidhtMenuPanel(), dashBoard.getProperties().getHeightMenuPanel());
        createBtns();
    }

    private void createBtns() {
        createBtnStart();
        createBtnStop();
    }

    public void createBtnStart() {
        btnStart = new JButton();
        btnStart.setText("Start");
        btnStart.setFocusable(false);
        this.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.presenter.start();
            }
        });
    }

    public void createBtnStop() {
        btnStop = new JButton();
        btnStop.setText("Stop");
        btnStop.setFocusable(false);
        this.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.presenter.stop();
            }
        });
    }
}
