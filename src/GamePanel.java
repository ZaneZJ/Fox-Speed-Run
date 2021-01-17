import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, Runnable {

  public static int WIDTH;
  public static int HEIGHT;

  private Thread animator;
  private boolean running = false;
  private boolean gameOver = false;
  
  private Ground ground;
  private ArrayList<Background> backgrounds;
  private Fox fox;
  private Obstacles obstacles;

  private int score;
  
  public GamePanel() {
    WIDTH = UserInterface.WIDTH;
    HEIGHT = UserInterface.HEIGHT;

    initialize();

    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }

  public void initialize() {
    score = 0;
    gameOver = false;
    SpeedLevel.setSpeedLevel(score);

    backgrounds = new ArrayList<>();
    backgrounds.add(new Background(0, "/Resources/images/Background/Main-Background.png"));
    backgrounds.add(new Background(2, "/Resources/images/Background/Second-Layer-Background.png"));
    backgrounds.add(new Background(4, "/Resources/images/Background/Third-Layer-Background.png"));

    ground = new Ground(HEIGHT, 10, "/Resources/images/Background/Ground-Background.png");
    fox = new Fox();
    obstacles = new Obstacles((int)(WIDTH * 1.5), fox, 10);

  }
  
  public void paint(Graphics g) {
    super.paint(g);

    for (Background background : backgrounds) {
      background.create(g);
    }
    ground.create(g);
    fox.create(g);
    obstacles.create(g);

    g.setFont(new Font("Courier New", Font.BOLD, 25));
    g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);
  }
  
  public void run() {
    running = true;

    while(running) {
      updateGame();
      repaint();      
      try {
        Thread.sleep(50);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void updateGame() {
    score += 1;
    SpeedLevel.setSpeedLevel(score);

    for (Background background : backgrounds) {
      background.update();
    }
    ground.update();
    obstacles.update();

    if(obstacles.hasCollided()) {
      fox.lose();
      repaint();
      running = false;
      gameOver = true;
      System.out.println("collide");
    }
  }

  public void reset() {
    initialize();
  }
  
  public void keyTyped(KeyEvent e) {
    if(e.getKeyChar() == ' ') {
      if (gameOver) {
        reset();
      }
      if (animator == null || !running) {
        System.out.println("Game starts");
        animator = new Thread(this);
        animator.start();
        fox.startRunning();
      }
    }
  }
  
  public void keyPressed(KeyEvent e) {
    if(e.getKeyChar() == ' ') {
        fox.jump();
    }
  }
  
  public void keyReleased(KeyEvent e) {
    // System.out.println("keyReleased: "+e);
  }
}