package com.ostan.gameoflife.mvp.game;

import android.app.Activity;

import com.ostan.gameoflife.mvp.interfaces.DataCallBackListener;
import com.ostan.gameoflife.mvp.interfaces.GameView;
import com.ostan.gameoflife.mvp.interfaces.GameViewPresenter;

/**
 * Created by marco on 27/02/2016.
 */
public class GamePresenterImpl implements GameViewPresenter, DataCallBackListener {

    GameModelImpl model;
    GameView view;
    int cellSizeX, cellSizeY;
    Activity activity;

    public GamePresenterImpl(Activity activity, GameView view, boolean[][] initialData) {
        this.model = new GameModelImpl(this, activity, initialData);
        this.view = view;
        this.activity = activity;
    }


    @Override
    public void updateData(final boolean[][] data) {
        view.updateView(data);
    }

    @Override
    public void pauseToggleClicked() {
        model.toggleGameRunningState();
        view.updatePauseToggle(model.logic.isGamePaused());
    }

    @Override
    public void cellClicked(int x, int y) {
        model.addTouch(x, y);
    }

    @Override
    public void gameSpeedChanged(int speed) {
        model.gameSpeedCahnged(speed);
    }

}
