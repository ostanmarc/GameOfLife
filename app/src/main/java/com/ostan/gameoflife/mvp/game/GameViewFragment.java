package com.ostan.gameoflife.mvp.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.ostan.gameoflife.Constants;
import com.ostan.gameoflife.R;
import com.ostan.gameoflife.mvp.interfaces.GameView;
import com.ostan.gameoflife.mvp.interfaces.GameViewPresenter;
import com.ostan.gameoflife.mvp.interfaces.TouchCallBackListener;
import com.ostan.gameoflife.ui.GameBoard;

/**
 * Created by marco on 27/02/2016.
 */
public class GameViewFragment extends Fragment implements GameView, TouchCallBackListener {

    Button togglePlayButton;
    SeekBar speedSeekBar;
    private final String KEY_PRESERVED_GAME_DATA = "gameData";
    private GameViewPresenter presenter;
    private GameBoard gameBoard;

    public boolean[][] getGameData() {
        return gameData;
    }

    private boolean[][] gameData = new boolean[Constants.GAME_FIELD_SIDE_SIZE][Constants.GAME_FIELD_SIDE_SIZE];

    public static final String TAG = "gameFragment";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOG","onCreateView savedInstanceState!= null"+(savedInstanceState!= null));
        boolean[][] data = null;
        if(savedInstanceState != null) {
             data = (boolean[][]) savedInstanceState.getSerializable(KEY_PRESERVED_GAME_DATA);
        }
        presenter = createPresenter(data);


        View view = inflater.inflate(R.layout.fragment_game, null);
        gameBoard = (GameBoard) view.findViewById(R.id.game_field);
        gameBoard.setTouchListener(this);

        togglePlayButton = (Button)view.findViewById(R.id.play_pause_btn);
        togglePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.pauseToggleClicked();
            }
        });

        speedSeekBar = (SeekBar) view.findViewById(R.id.game_speed_seekBar);
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.gameSpeedChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    @NonNull
    public GameViewPresenter createPresenter(boolean[][] initialData) {
        return new GamePresenterImpl(getActivity(), this, initialData);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_PRESERVED_GAME_DATA, gameData);
        Log.i("LOG","SAVED DATA: "+gameData.toString());
    }

    @Override
    public void updateView(boolean[][] data) {
        gameBoard.updateView(data);
        gameData = data;
    }

    @Override
    public void updatePauseToggle(boolean isPaused) {
        if(isPaused) {
            togglePlayButton.setText(getString(R.string.start_playing));
        } else {
            togglePlayButton.setText(getString(R.string.pause_playing));
        }
    }

    @Override
    public void cellClicked(int x, int y) {
        presenter.cellClicked(x, y);
    }

}
