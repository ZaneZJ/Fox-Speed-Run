package com.zane.components;

import com.zane.utility.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fox {

    private enum FoxState {
        STANDING,
        RUNNING,
        JUMPING,
        LOST
    }

    private int offsetX, offsetY;
    private int foxTopY, foxStartX;

    private int foxTop, initialTopPoint;
    private boolean topPointReached;

    private int jumpFactor = 15;
    private int jumpStrength = 0;
    private int maxJumpStrength = 5;

    private FoxState state;
    private int runningIndex;
    private int updateAt;
    private int updateIndex;

    BufferedImage standingFox;
    BufferedImage lostFox;
    BufferedImage jumpingFox;
    ArrayList<BufferedImage> runningFoxes;

    public Fox() {
        standingFox = new Resource().getResourceImage("/images/Fox/1-1-Fox.png");
        lostFox = new Resource().getResourceImage("/images/Fox/Lost-Fox.png");
        jumpingFox = new Resource().getResourceImage("/images/Fox/Jumping-Fox.png");

        runningFoxes = new ArrayList<>();
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/1-1-Fox.png"));
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/1-2-Fox.png"));
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/1-3-Fox.png"));
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/2-1-Fox.png"));
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/2-2-Fox.png"));
        runningFoxes.add(new Resource().getResourceImage("/images/Fox/2-3-Fox.png"));

        offsetX = 60;
        offsetY = 40;

        foxTopY = Ground.GROUND_Y - standingFox.getHeight() + offsetY;
        foxStartX = 100 + offsetX;
        initialTopPoint = foxTopY - 150;

        state = FoxState.STANDING;
        runningIndex = 0;
        // Update image every n frames. Higher updateAt -> Slower image update
        updateAt = 5;
        updateIndex = 0;
    }

    public void create(Graphics g) {
        switch (state) {
            case STANDING:
                System.out.println("stand");
                g.drawImage(standingFox, foxStartX, foxTopY, null);
                break;

            case RUNNING:
                BufferedImage image = runningFoxes.get(runningIndex);
                g.drawImage(image, foxStartX, foxTopY, null);
                // Get next index and use remainder to loop to start of
                // the list instead of overflowing
                if (updateIndex++ >= updateAt) {
                    runningIndex = (runningIndex + 1) % runningFoxes.size();
                    updateIndex = 0;
                }
                break;

            case JUMPING:
                // Jump up by jumpFactor
                int topPoint = initialTopPoint - jumpStrength * jumpFactor;
                if (foxTop > topPoint && !topPointReached) {
                    g.drawImage(jumpingFox, foxStartX, foxTop -= jumpFactor, null);
                    break;
                }
                // Have reached the maximum jump height, time to fall down
                else if (foxTop <= topPoint && !topPointReached) {
                    topPointReached = true;
                    g.drawImage(jumpingFox, foxStartX, foxTop += jumpFactor, null);
                    break;
                }
                // Falling down after jump
                else if (foxTop > topPoint && topPointReached) {
                    // Reached ground
                    if (foxTopY <= foxTop && topPointReached) {
                        state = FoxState.RUNNING;
                        topPointReached = false;
                        break;
                    }
                    g.drawImage(jumpingFox, foxStartX, foxTop += jumpFactor, null);
                    break;
                }
            case LOST:
                g.drawImage(lostFox, foxStartX, foxTop, null);
                break;
        }
    }

    public void lose() {
        state = FoxState.LOST;
    }

    public Rectangle getFox() {
        Rectangle fox = new Rectangle();

        if (state == FoxState.JUMPING && !topPointReached) fox.y = foxTop - jumpFactor;
        else if (state == FoxState.JUMPING && topPointReached) fox.y = foxTop + jumpFactor;
        else if (state != FoxState.JUMPING) fox.y = foxTop;

        fox.y += offsetY;
        // Offset the fox's tail more than its front
        fox.x = (int) (foxStartX + offsetX * 1.5);

        fox.width = (int) (standingFox.getWidth() - 2.5 * offsetX);
        fox.height = standingFox.getHeight() - 2 * offsetY;

        return fox;
    }

    public void startRunning() {
        foxTop = foxTopY;
        state = FoxState.RUNNING;
    }

    public void jump() {
        if (state != FoxState.JUMPING) {
            foxTop = foxTopY;
            topPointReached = false;
            jumpStrength = 0;
            state = FoxState.JUMPING;
        } else {
            if (jumpStrength < maxJumpStrength) {
                jumpStrength++;
            }
        }
    }
}