package com.ostan.gameoflife.mvp.interfaces;

/**
 * Created by marco on 27/02/2016.
 */
public interface DataCallBackListener {
    // methods
    /** This method passes data from Model to dataCallBackListener*/
    public void updateData(boolean[][] data);

}
