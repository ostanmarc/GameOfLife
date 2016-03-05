package com.ostan.gameoflife.mvp.interfaces;

public interface GameView {

    public void updateView(boolean[][] data);
    public void updatePauseToggle(boolean isPaused);
}
 