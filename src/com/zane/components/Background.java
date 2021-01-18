package com.zane.components;

import com.zane.utility.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Background {

    private class BackgroundImage {
        private BufferedImage image;
        int x;
    }

    protected int speed;
    protected int backgroundY;
    protected List<BackgroundImage> backgroundImageList;

    public Background(int speed, String imagePath) {
        this.speed = speed;
        backgroundY = 0;
        backgroundImageList = new ArrayList<>();

        // Creating background image list which will render images in order
        BufferedImage image = new Resource().getResourceImage(imagePath);
        for (int i = 0; i < 2; i++) {
            BackgroundImage obj = new BackgroundImage();
            obj.image = image;
            obj.x = 0;
            backgroundImageList.add(obj);
        }
    }

    public void update() {
        Iterator<BackgroundImage> looper = backgroundImageList.iterator();
        BackgroundImage first = looper.next();

        // Move all images to the right by speed amount
        first.x -= speed + SpeedLevel.getSpeedLevel();
        int previousX = first.x;
        while (looper.hasNext()) {
            BackgroundImage next = looper.next();
            next.x = previousX + next.image.getWidth() - 1;
            previousX = next.x;
        }

        // If first image is off the screen, remove it and add it at the end
        if (first.x < -first.image.getWidth()) {
            backgroundImageList.remove(first);
            first.x = previousX + first.image.getWidth() - 1;
            backgroundImageList.add(first);
        }
    }

    public void create(Graphics g) {
        for (BackgroundImage image : backgroundImageList) {
            g.drawImage(image.image, image.x, backgroundY, null);
        }
    }


}
