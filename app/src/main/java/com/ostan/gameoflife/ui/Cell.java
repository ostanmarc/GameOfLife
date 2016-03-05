package com.ostan.gameoflife.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ostan.gameoflife.mvp.interfaces.TouchCallBackListener;

public final class Cell {
    private static final String TAG = Cell.class.getSimpleName();

    private final int col;
    private final int row;

    private final Paint squareColor;
    private Rect tileRect;
    TouchCallBackListener touchListener;
    boolean isAlive;

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
        squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);
    }

    public Cell(final int col, final int row, TouchCallBackListener presenter) {
        this.col = col;
        this.row = row;
        this.touchListener = presenter;

        this.squareColor = new Paint();
        squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);
    }

    public void draw(final Canvas canvas) {
        canvas.drawRect(tileRect, squareColor);
    }

    public void handleTouch() {
        if(touchListener != null) {
            touchListener.cellClicked(col,row);
        }

    }

    public boolean isDark() {
        return isAlive;
    }

    public boolean isTouched(final int x, final int y) {
        return tileRect.contains(x, y);
    }

    public void setTileRect(final Rect tileRect) {
        this.tileRect = tileRect;
    }


}