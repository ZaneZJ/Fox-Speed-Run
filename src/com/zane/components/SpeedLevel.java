package com.zane.components;

public class SpeedLevel {
    private static final int INCREASE_SPEED_LEVEL_EVERY = 300;
    private static int SPEED_LEVEL;

    public static void setSpeedLevel(int score) {
        SPEED_LEVEL = score / INCREASE_SPEED_LEVEL_EVERY;
    }

    public static int getSpeedLevel() {
        return SPEED_LEVEL;
    }
}
