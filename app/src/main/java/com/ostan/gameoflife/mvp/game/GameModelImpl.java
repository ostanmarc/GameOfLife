package com.ostan.gameoflife.mvp.game;

import android.app.Activity;

import com.ostan.gameoflife.logics.LogicImpl;
import com.ostan.gameoflife.logics.MainLoop;
import com.ostan.gameoflife.mvp.interfaces.DataCallBackListener;
import com.ostan.gameoflife.mvp.interfaces.GameModel;

/**
 * Created by marco on 27/02/2016.
 */
public class GameModelImpl implements GameModel {

    DataCallBackListener dataCallBackListener = null;
    private static MainLoop loopRunnable;
    LogicImpl logic;
    public GameModelImpl(final DataCallBackListener presenter, final Activity activity, boolean[][] data) {
        this.dataCallBackListener = presenter;
        logic = new LogicImpl(data);

        if(loopRunnable != null) {
            loopRunnable.setShouldStop(true);
        }
        loopRunnable = new MainLoop(dataCallBackListener, activity, logic);
        new Thread(loopRunnable).start();
    }

    @Override
    public void addTouch(int x, int y) {
        logic.addTouch(x, y);

    }

    @Override
    public void toggleGameRunningState() {
        logic.toggleGameRunningState();
    }

    @Override
    public void gameSpeedCahnged(int speed) {
        logic.setGameSpeed(speed);
    }


}
