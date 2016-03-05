package com.ostan.gameoflife;

import android.test.suitebuilder.annotation.SmallTest;

import com.ostan.gameoflife.logics.LogicImpl;

import junit.framework.TestCase;

/**
 * Created by marco on 04/03/2016.
 */
public class GameOfLifeLogicUnitTests extends TestCase {
    LogicImpl logic;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        logic = new LogicImpl(new boolean[Constants.GAME_FIELD_SIDE_SIZE][Constants.GAME_FIELD_SIDE_SIZE]);
    }

    @SmallTest
    public void testCellBirthLogic() {
        assertTrue("Reborn cell", logic.calculateLogicsForCell(false, 3));
    }

    @SmallTest
    public void testCellDeathByUnderPopulationLogic() {
        assertEquals("Dying cell", false, logic.calculateLogicsForCell(true, 1));;
    }

    @SmallTest
    public void testCellDeathOverPopulationLogic() {
        assertEquals("Dying cell", false, logic.calculateLogicsForCell(true, 4));
    }

    @SmallTest
    public void testCellLivingNextGenerationLogic() {
        assertEquals("Living cell", true, logic.calculateLogicsForCell(true, 2));
        assertEquals("Living cell", true, logic.calculateLogicsForCell(true, 3));
    }

}
