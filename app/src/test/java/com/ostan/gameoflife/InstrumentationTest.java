package com.ostan.gameoflife;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ostan.gameoflife.activities.GameActivity;
import com.ostan.gameoflife.mvp.game.GameViewFragment;
import com.ostan.gameoflife.ui.GameBoard;

/**
 * Created by marco on 05/03/2016.
 */
public class InstrumentationTest extends ActivityInstrumentationTestCase2<GameActivity> {


    public InstrumentationTest(){
        super(GameActivity.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testPauseButton(){

        GameViewFragment fragment = (GameViewFragment)getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG);
        assertNotNull(fragment);
        Button pauseButton = (Button)fragment.getView().findViewById(R.id.play_pause_btn);
        assertNotNull(pauseButton);
    }


    @SmallTest
    public void testFragmentHolder(){
        FrameLayout frameLayout = (FrameLayout)getActivity().findViewById(R.id.fragment_container);
        assertNotNull(frameLayout);
    }

    @SmallTest
    public void testGameField(){
        FrameLayout frameLayout = (FrameLayout)getActivity().findViewById(R.id.fragment_container);
        GameBoard board = (GameBoard)frameLayout.findViewById(R.id.game_field);
        assertNotNull(board);
    }

}
