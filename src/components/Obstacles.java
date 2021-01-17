package components;

import utility.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Obstacles {
    private class Obstacle {
        BufferedImage image;
        int x;
        int y;

        Rectangle getObstacle() {
            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            obstacle.y = y;
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();

            return obstacle;
        }
    }

    private int firstX;
    private int movementSpeed;

    private ArrayList<BufferedImage> imageList;
    private ArrayList<Obstacle> obList;

    private Fox fox;

    // random number in the interval for obstacles
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private BufferedImage getRandomImage() {
        Random random = new Random();
        int index = random.nextInt(imageList.size());
        return imageList.get(index);
    }

    private Obstacle getRandomObstacle(int x) {
        Obstacle obstacle = new Obstacle();
        BufferedImage image = getRandomImage();

        obstacle.image = image;
        obstacle.x = x;
        obstacle.y = Ground.GROUND_Y - image.getHeight() + 5;

        return obstacle;
    }

    public Obstacles(int firstPos, Fox fox, int speed) {
        this.fox = fox;
        obList = new ArrayList<>();
        imageList = new ArrayList<>();

        firstX = firstPos;
        movementSpeed = speed;

        imageList.add(new Resource().getResourceImage("/Resources/images/Obstacles/Basic-Obstacle.png"));
        imageList.add(new Resource().getResourceImage("/Resources/images/Obstacles/Tall-Obstacle.png"));
        imageList.add(new Resource().getResourceImage("/Resources/images/Obstacles/Wide-Obstacle.png"));

        int x = firstX;
        int obstaclesToRender = 5;

        for (int i = 0; i < obstaclesToRender; i++) {

            Obstacle obstacle = getRandomObstacle(x);
            // Obstacle distance
            x += getRandomNumber(400, 700);

            obList.add(obstacle);
        }
    }

    public void update() {
        Iterator<Obstacle> looper = obList.iterator();

        Obstacle firstOb = looper.next();
        firstOb.x -= movementSpeed + SpeedLevel.getSpeedLevel();

        while (looper.hasNext()) {
            Obstacle ob = looper.next();
            ob.x -= movementSpeed + SpeedLevel.getSpeedLevel();
        }

        if (firstOb.x < -firstOb.image.getWidth()) {
            obList.remove(firstOb);
            int newObstacleX = obList.get(obList.size() - 1).x + getRandomNumber(400, 700);
            Obstacle newObstacle = getRandomObstacle(newObstacleX);
            obList.add(newObstacle);
        }
    }

    public void create(Graphics g) {
        for (Obstacle ob : obList) {
            g.setColor(Color.black);
            g.drawImage(ob.image, ob.x, ob.y, null);
        }
    }

    public boolean hasCollided() {
        for (Obstacle ob : obList) {
            if (fox.getFox().intersects(ob.getObstacle())) {
                System.out.println("Fox = " + fox.getFox() + "\nObstacle = " + ob.getObstacle() + "\n\n");
                return true;
            }
        }
        return false;
    }
}