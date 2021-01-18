package com.zane;

import com.zane.models.HighScore;
import com.zane.utility.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class HighScoresPanel extends JPanel {

    private static final int LIST_WIDTH = 400;
    private static final int LIST_HEIGHT = 400;
    
    JList list;
    ActionListener playAgainButtonAction;
    ActionListener mainMenuButtonAction;

    public HighScoresPanel(ActionListener playAgainButtonAction, ActionListener mainMenuButtonAction) {
        this.playAgainButtonAction = playAgainButtonAction;
        this.mainMenuButtonAction = mainMenuButtonAction;
        
        setLayout(null);
        addTitle();
        addScrollableList();
        addButtons();
    }

    // top line maybe change
    private void addTitle() {
        JLabel title = new JLabel("High scores");
        title.setFont(title.getFont().deriveFont(30f));
        title.setBounds(UserInterface.WIDTH / 2 - LIST_WIDTH / 2,
                UserInterface.HEIGHT / 2 - LIST_HEIGHT / 2 - 100,
                LIST_WIDTH,
                100);
        add(title);
    }

    private void addScrollableList() {
        list = new JList();
        list.setFont(list.getFont().deriveFont(20f));
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(UserInterface.WIDTH / 2 - LIST_WIDTH / 2,
                UserInterface.HEIGHT / 2 - LIST_HEIGHT / 2,
                LIST_WIDTH,
                LIST_HEIGHT);
        add(scrollPane);
    }

    private void addButtons() {
        int buttonSpacing = 20;
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(UserInterface.WIDTH / 2 - LIST_WIDTH / 2,
                UserInterface.HEIGHT / 2 - LIST_HEIGHT / 2 + LIST_HEIGHT + 20,
                LIST_WIDTH / 2 - buttonSpacing / 2,
                50);
        playAgainButton.setFont(playAgainButton.getFont().deriveFont(20f));
        playAgainButton.addActionListener(playAgainButtonAction);
        add(playAgainButton);

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(UserInterface.WIDTH / 2 + buttonSpacing / 2,
                UserInterface.HEIGHT / 2 - LIST_HEIGHT / 2 + LIST_HEIGHT + 20,
                LIST_WIDTH / 2 - buttonSpacing / 2,
                50);
        mainMenuButton.setFont(mainMenuButton.getFont().deriveFont(20f));
        mainMenuButton.addActionListener(mainMenuButtonAction);
        add(mainMenuButton);
    }

    public void update() {
        List<HighScore> highScores = new HighScoresRepository().getHighScores();
        String[] listData = new String[highScores.size()];
        for (int i = 0; i < highScores.size(); i++) {
            String name = highScores.get(i).getName();
            int score = highScores.get(i).getScore();
            listData[i] = String.format("%d. %s - %d", i + 1, name, score);
        }
        list.setListData(listData);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new Resource().getResourceImage("/images/Main/Extra-Screen.png");
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
