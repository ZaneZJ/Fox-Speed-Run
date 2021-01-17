import utility.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainMenu extends JPanel {
    JTextField nameTextField;
    JButton playButton;
    JButton highScoresButton;

    public MainMenu(ActionListener playButtonAction, ActionListener highScoresButtonAction) {
        setLayout(null);
        addNameTextField();
        addPlayButton(playButtonAction);
        addHighScoresButton(highScoresButtonAction);
    }

    private void addNameTextField() {
        nameTextField = new JTextField(10);
        nameTextField.setBounds(600 - 280 / 2, 550 - 50 / 2, 280, 50);
        nameTextField.setFont(nameTextField.getFont().deriveFont(30f));
        nameTextField.setText("Player");
        add(nameTextField);
    }

    private void addPlayButton(ActionListener playButtonAction) {
        playButton = new JButton("Play");
        playButton.setBounds(600 - 280 / 2, 620 - 50 / 2, 280, 50);
        playButton.setFont(playButton.getFont().deriveFont(30f));
        playButton.addActionListener(playButtonAction);
        add(playButton);
    }

    private void addHighScoresButton(ActionListener highScoresButtonAction) {
        highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(600 - 280 / 2, 690 - 50 / 2, 280, 50);
        highScoresButton.setFont(highScoresButton.getFont().deriveFont(30f));
        highScoresButton.addActionListener(highScoresButtonAction);
        add(highScoresButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new Resource().getResourceImage("/Resources/images/Main/Main-Screen.png");
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
