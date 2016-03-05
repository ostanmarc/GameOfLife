package com.ostan.gameoflife.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ostan.gameoflife.R;
import com.ostan.gameoflife.mvp.game.GameViewFragment;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Prevent fragment double recreation on device rotation
        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new GameViewFragment(), GameViewFragment.TAG).commit();

        }


    }

}
