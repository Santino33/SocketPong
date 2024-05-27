package co.edu.uptc.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingRoom extends JFrame {
    private JLabel playerCountLabel;
    private JButton startGameButton;
    private int playerCount;
    private JPanel mainPanel;
    private boolean ended;

    public WaitingRoom() {
        initializeComponents();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new GridBagLayout());

        ended = false;
        playerCount = 0;
        playerCountLabel = new JLabel("Players in waiting room: " + playerCount);
        playerCountLabel.setForeground(Color.BLACK);
        playerCountLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButtonColor();
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setFocusPainted(false);
        startGameButton.setPreferredSize(new Dimension(120, 40));

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(playerCountLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(startGameButton, gbc);
    }

    public void start() {
        setTitle("Waiting Room");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        add(mainPanel);
        setVisible(true);
    }

    public void updatePlayerCount(int newCount) {
        playerCount = newCount;
        playerCountLabel.setText("Players in waiting room: " + playerCount);
        updateButtonColor();
    }

    private void updateButtonColor() {
        if (playerCount < 2) {
            startGameButton.setBackground(new Color(169, 169, 169));
            startGameButton.setEnabled(false);
        } else {
            startGameButton.setBackground(new Color(75, 110, 175));
            startGameButton.setEnabled(true);
        }
    }

    private void startGame() {
        if (playerCount >= 2) {
            ended = true;
            dispose();
        }
    }

    public boolean isEnded() {
        return ended;
    }
}

