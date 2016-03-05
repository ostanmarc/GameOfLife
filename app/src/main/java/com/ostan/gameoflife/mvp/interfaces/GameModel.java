package com.ostan.gameoflife.mvp.interfaces;

/**
 * Created by marco on 27/02/2016.
 */
public interface GameModel {
    public void addTouch(int x, int y);
    public void toggleGameRunningState();
    public void gameSpeedCahnged(int speed);
}
