package com.zane.components;

public class Ground extends Background {

    public static int GROUND_Y;

    public Ground(int panelHeight, int speed, String image) {
        super(speed, image);
        // GROUND_Y is where to draw elements on the ground
        // backgroundY is where to draw the ground image
        GROUND_Y = (int) (panelHeight - 0.15 * panelHeight);
        backgroundY = GROUND_Y - 35;
    }
}