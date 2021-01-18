package com.zane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface implements GameOverListener {
    JFrame mainWindow = new JFrame("Fox Speed Run");
    MainMenuPanel mainMenuPanel;
    GamePanel gamePanel;
    HighScoresPanel highScoresPanel;

    public static int WIDTH = 1200;
    public static int HEIGHT = 900;

    public void createAndShowGUI() {
        Container container = mainWindow.getContentPane();
        container.setLayout(new CardLayout());

        gamePanel = new GamePanel(this);
        container.add(gamePanel, "Game");

        highScoresPanel = new HighScoresPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play again button clicked
                showGamePanel();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Main menu
                showMainMenuPanel();
            }
        });
        container.add(highScoresPanel, "High Scores");

        mainMenuPanel = new MainMenuPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play game button clicked
                showGamePanel();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // High scores button clicked
                showHighScoresPanel();
            }
        });
        container.add(mainMenuPanel, "Main Menu");

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        showMainMenuPanel();
    }

    public void showGamePanel() {
        gamePanel.setFocusable(true);
        gamePanel.transferFocus();
        showPanel("Game");
    }

    public void showHighScoresPanel() {
        showPanel("High Scores");
        highScoresPanel.update();
    }

    public void showMainMenuPanel() {
        showPanel("Main Menu");
    }

    public void showPanel(String panelName) {
        Container container = mainWindow.getContentPane();
        CardLayout layout = (CardLayout) container.getLayout();
        layout.show(container, panelName);
    }

    public static void main(String[] args) {
        // Creates the GUI once Swing is ready
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().createAndShowGUI();
            }
        });
    }

    @Override
    public void onGameOver(int score) {
        String playerName = mainMenuPanel.getPlayerName();
        new HighScoresRepository().putHighScore(playerName, score);
        highScoresPanel.update();
        showHighScoresPanel();
    }
}