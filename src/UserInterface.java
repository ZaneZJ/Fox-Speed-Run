import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
  JFrame mainWindow = new JFrame("Fox Speed Run");
  MainMenu mainMenu;
  GamePanel gamePanel;

  
  public static int WIDTH = 1200;
  public static int HEIGHT = 900;

  public void createAndShowGUI() {
    Container container = mainWindow.getContentPane();
    container.setLayout(new BorderLayout());

    gamePanel = new GamePanel();
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(false);
    gamePanel.setVisible(false);
    container.add(gamePanel, BorderLayout.CENTER);

    mainMenu = new MainMenu(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Play game button clicked
        hideMainMenu();
        showGamePanel();
      }
    }, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // High scores button clicked
      }
    });
    mainMenu.setFocusable(false);
    mainMenu.setVisible(false);
    container.add(mainMenu, BorderLayout.CENTER);

    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(false);
    mainWindow.setVisible(true);
    showMainMenu();
  }

  public void showGamePanel() {
    gamePanel.setVisible(true);
    gamePanel.setFocusable(true);
    gamePanel.requestFocusInWindow();
  }

  public void hideGamePanel() {
    gamePanel.setFocusable(false);
    gamePanel.setVisible(false);
  }

  public void showMainMenu() {
    mainMenu.setFocusable(true);
    mainMenu.setVisible(true);
    mainMenu.requestFocusInWindow();
  }

  public void hideMainMenu() {
    mainMenu.setFocusable(false);
    mainMenu.setVisible(false);
  }
  
  public static void main(String[] args) {
    // palaidis funkciju kad viss ir gatavs
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}