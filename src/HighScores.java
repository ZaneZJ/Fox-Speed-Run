import utility.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class HighScores extends JPanel {

    JTextField textField;
    JTextField top1TextField;
    JTextField top2TextField;
    JTextField top3TextField;

    private String top1;
    private String top2;
    private String top3;

    private void addTextField() {
        textField = new JTextField("Top 3!");
        textField.setBounds(600 - 280 / 2, 620 - 50 / 2, 280, 50);
        textField.setFont(textField.getFont().deriveFont(30f));
        add(textField);
    }

    private void addTop1TextField() {
        top1TextField = new JTextField(top1);
        top1TextField.setBounds(600 - 280 / 2, 620 - 50 / 2, 280, 50);
        top1TextField.setFont(top1TextField.getFont().deriveFont(30f));
        add(top1TextField);
    }

    private void addTop2TextField() {
        top2TextField = new JTextField(top2);
        top2TextField.setBounds(600 - 280 / 2, 620 - 50 / 2, 280, 50);
        top2TextField.setFont(top2TextField.getFont().deriveFont(30f));
        add(top2TextField);
    }

    private void addTop3TextField() {
        top3TextField = new JTextField(top3);
        top3TextField.setBounds(600 - 280 / 2, 620 - 50 / 2, 280, 50);
        top3TextField.setFont(top3TextField.getFont().deriveFont(30f));
        add(top3TextField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new Resource().getResourceImage("/Resources/images/Main/Extra-Screen.png");
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

}
