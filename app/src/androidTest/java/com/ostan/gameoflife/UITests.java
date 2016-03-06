package com.ostan.gameoflife;

import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.ostan.gameoflife.activities.GameActivity;
import com.ostan.gameoflife.mvp.game.GameViewFragment;
import com.ostan.gameoflife.ui.GameBoard;

/**
 * Created by marco on 06/03/2016.
 */
public class UITests extends ActivityInstrumentationTestCase2<GameActivity> {

    public UITests() {
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
    public void testUiExistance() {
        assertNotNull(getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG));

    }

    @SmallTest
    public void testFragmentExistance() {
        assertNotNull(getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG));

    }

    @SmallTest
    public void testFragmentView() {
        View view = getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG).getView();
        assertNotNull(view);
    }

    @SmallTest
    public void testButtonExistance() {
        View view = getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG).getView();
        Button button = (Button) view.findViewById(R.id.play_pause_btn);
        assertNotNull(button);
    }

    @SmallTest
    public void testSeekBarExistance() {
        View view = getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG).getView();
        SeekBar seekbar = (SeekBar) view.findViewById(R.id.game_speed_seekBar);
        assertNotNull(seekbar);
    }

    @SmallTest
    @UiThreadTest
    public void testOnClick() {
        Button button = getPauseButton();
        button.callOnClick();
        assertEquals(button.getText().toString(), getActivity().getString(R.string.start_playing));
    }

    @SmallTest
    @UiThreadTest
    public void testBoardClick() {
        GameBoard board = getGameBoard();
        assertNotNull(board);
        GameViewFragment fragment = (GameViewFragment) getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG);
        // todo handle the touch coordination for specified cell
        boolean cellState = fragment.getGameData()[0][0];
        board.onTouchEvent(getTouchEvent(0.1f, 0.1f));
        assertEquals(!cellState, fragment.getGameData()[0][0]);

    }

    private View getFragmentView() {
        return getActivity().getSupportFragmentManager().findFragmentByTag(GameViewFragment.TAG).getView();
    }

    private Button getPauseButton() {
        return (Button) getFragmentView().findViewById(R.id.play_pause_btn);
    }

    private SeekBar getSeekBar() {
        return (SeekBar) getFragmentView().findViewById(R.id.game_speed_seekBar);
    }

    private GameBoard getGameBoard() {
        return (GameBoard) getFragmentView().findViewById(R.id.game_field);
    }

    private MotionEvent getTouchEvent(float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;

        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
        );
        return motionEvent;
    }
}
