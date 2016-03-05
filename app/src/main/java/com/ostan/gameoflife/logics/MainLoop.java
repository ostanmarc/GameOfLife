package com.ostan.gameoflife.logics;

import android.app.Activity;

import com.ostan.gameoflife.mvp.interfaces.DataCallBackListener;

/**
 * Created by marco on 01/03/2016.
 */
public class MainLoop implements Runnable {

    DataCallBackListener dataCallBackListener;
    Activity activity;
    LogicImpl logic;
    boolean shouldStop = false;

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public MainLoop(DataCallBackListener dataCallBackListener, Activity activity, LogicImpl logic) {
        this.dataCallBackListener = dataCallBackListener;
        this.activity = activity;
        this.logic = logic;
    }

    @Override
    public void run() {

        while(true && !shouldStop) {

            logic.executeLogicOperations();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dataCallBackListener.updateData(logic.getLifeMap());
                }
            });
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
