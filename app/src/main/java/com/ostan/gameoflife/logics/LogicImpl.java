package com.ostan.gameoflife.logics;

import android.util.Pair;

import com.ostan.gameoflife.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 01/03/2016.
 */
public class LogicImpl implements com.ostan.gameoflife.logics.Logic {
    boolean[][] lifeMap;
    boolean isGamePaused = false;
    int gameSpeed;
    private final int MINIMUM_SPEED_MULTIPLIER = 10;

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    int loginCounter = 0;

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public boolean[][] getLifeMap() {
        return lifeMap;
    }

    /**
     * Constructor
     * @param lifeMap - will be not null in case device was rotated
     *                and we have preserved data that we want to apply to newly created LogicImpl instance
     * */
    public LogicImpl(boolean[][] lifeMap) {
        if (lifeMap == null) {
            this.lifeMap = new boolean[Constants.GAME_FIELD_SIDE_SIZE][Constants.GAME_FIELD_SIDE_SIZE];
        } else {
            this.lifeMap = lifeMap;
        }
        touches = new ArrayList<>();
    }

    List<Pair<Integer, Integer>> touches;


    @Override
    public void addTouch(int x, int y) {
        touches.add(new Pair<Integer, Integer>(x, y));
    }

    /**
     * Apply all the touches that happened since the last time map was recalculated
     * */
    private void calculateTouches() {
        for (Pair<Integer, Integer> iterator : touches) {
            lifeMap[iterator.first][iterator.second] = !lifeMap[iterator.first][iterator.second];
        }
        touches.clear();
    }

    /**
     * Method for pause\resume simulation
     * */
    @Override
    public void toggleGameRunningState() {
        isGamePaused = !isGamePaused;
    }

    /**
     * Perform the logic operations of combining life\death generations logics
     * and touches
     * */
    public void executeLogicOperations() {

        // Prevent too fast living logics calculations
        if (loginCounter > (MINIMUM_SPEED_MULTIPLIER - gameSpeed)) {
            if (!isGamePaused()) {
                calculateCellLivingLogics();
            }
            loginCounter = 0;
        } else {
            loginCounter++;
        }

        calculateTouches();
    }

    /**
     * Run through the data and calculate next generation according to the livingLogics
     */
    public void calculateCellLivingLogics() {
        boolean[][] tempLifeMap = new boolean[Constants.GAME_FIELD_SIDE_SIZE][Constants.GAME_FIELD_SIDE_SIZE];
        for (int row = 0; row < Constants.GAME_FIELD_SIDE_SIZE; row++) {
            for (int col = 0; col < Constants.GAME_FIELD_SIDE_SIZE; col++) {
                int neibours = countNeibours(col, row);
                tempLifeMap[col][row] = calculateLogicsForCell(lifeMap[col][row], neibours);
            }
        }
        lifeMap = tempLifeMap;
    }

    /**
     * Calculate Cell's live or Death according to its position and current state
     * @param cellState - true in case cell is alive
     * @param - number of cell's neibors
     * */
    public boolean calculateLogicsForCell(boolean cellState, int neibours) {
        boolean result = cellState;
        if (cellState) {
//                    live cell with fewer than two live neighbours dies, as if caused by under-population
            if (neibours < 2) {
                result = false;

            }
//                    live cell with more than three live neighbours dies, as if by over-population.
            if (neibours > 3) {
                result = false;
            }
//                    live cell with two or three live neighbours lives on to the next generation.
//                    no change needed

        } else {
            // Dead cell will become alive in case it has 3 neibors as a result of reproduction
            if (neibours == 3) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method that calculates number of alive neibours around the provided cell coordinates
     *
     * @param col - column number
     * @param row - row number
     */
    private int countNeibours(int col, int row) {
        int result = 0;

        int[] bounds = new int[4];
        bounds[0] = col - 1; // startX
        bounds[1] = col + 1; // endX
        bounds[2] = row - 1; // startY
        bounds[3] = row + 1; // endY


        for (int i = 0; i < bounds.length; i++) {
            if (bounds[i] < 0) {
                bounds[i] = 0;
            }
            if (bounds[i] >= Constants.GAME_FIELD_SIDE_SIZE) {
                bounds[i] = Constants.GAME_FIELD_SIDE_SIZE - 1;
            }
        }

        for (int rowI = bounds[2]; rowI <= bounds[3]; rowI++) {
            for (int colI = bounds[0]; colI <= bounds[1]; colI++) {
                if (row != rowI || colI != col) {
                    if (lifeMap[colI][rowI]) {
                        result++;
                    }
                }
            }
        }

        return result;
    }


}
