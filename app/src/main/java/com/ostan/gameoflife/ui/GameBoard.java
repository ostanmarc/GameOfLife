package com.ostan.gameoflife.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ostan.gameoflife.Constants;
import com.ostan.gameoflife.mvp.interfaces.TouchCallBackListener;

/**
 * Created by marco on 01/03/2016.
 */
public class GameBoard extends View {
    private static final String TAG = GameBoard.class.getSimpleName();

    private static final int COLS = Constants.GAME_FIELD_SIDE_SIZE;
    private static final int ROWS = Constants.GAME_FIELD_SIDE_SIZE;

    private final Cell[][] cells;

    private int x0 = 0;
    private int y0 = 0;
    private int squareSize = 0;

    /**
     * 'true' if black is facing player.
     */
    private boolean flipped = false;
    private TouchCallBackListener touchListener;

    public void setTouchListener(TouchCallBackListener touchListener) {
        this.touchListener = touchListener;
        buildTiles();
    }

    public GameBoard(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.cells = new Cell[COLS][ROWS];

        setFocusable(true);

        buildTiles();
    }

    private void buildTiles() {
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                cells[c][r] = new Cell(c, r, touchListener);
            }
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        final int width = getWidth();
        final int height = getHeight();

        this.squareSize = Math.min(
                getSquareSizeWidth(width),
                getSquareSizeHeight(height)
        );

        computeOrigins(width, height);

        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                final int xCoord = getXCoord(c);
                final int yCoord = getYCoord(r);

                final Rect tileRect = new Rect(xCoord, yCoord, xCoord + squareSize, yCoord + squareSize);

                cells[c][r].setTileRect(tileRect);
                cells[c][r].draw(canvas);
            }
        }
    }

    public void setMatrix(boolean[][] src) {
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                cells[c][r].setIsAlive(src[c][r]);
            }
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_UP) {
            return true;
        }
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        Cell tile;
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                tile = cells[c][r];
                if (tile.isTouched(x, y))
                    tile.handleTouch();
            }
        }

        return true;
    }

    private int getSquareSizeWidth(final int width) {
        return width / ROWS;
    }

    private int getSquareSizeHeight(final int height) {
        return height / COLS;
    }

    private int getXCoord(final int x) {
        return x0 + squareSize * (Constants.GAME_FIELD_SIDE_SIZE - 1 - x);
    }

    private int getYCoord(final int y) {
        return y0 + squareSize * (Constants.GAME_FIELD_SIDE_SIZE - 1 - y);
    }

    private void computeOrigins(final int width, final int height) {
        this.x0 = (width - squareSize * Constants.GAME_FIELD_SIDE_SIZE) / 2;
        this.y0 = (height - squareSize * Constants.GAME_FIELD_SIDE_SIZE) / 2;
    }


    public void updateView(boolean[][] data) {
        setMatrix(data);
        invalidate();
    }

}


