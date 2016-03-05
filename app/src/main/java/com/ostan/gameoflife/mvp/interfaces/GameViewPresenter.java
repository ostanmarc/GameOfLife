package com.ostan.gameoflife.mvp.interfaces;

/**
 * Created by marco on 27/02/2016.
 */
public interface GameViewPresenter {
    public void pauseToggleClicked();
    public void cellClicked(int x, int y);
    public void gameSpeedChanged(int speed);
}
