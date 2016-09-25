package com.ethannjc.project3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) return;

        int gameSize = getIntent().getIntExtra("EXTRA_GAMESIZE", 4);
        if (gameSize == 4) setContentView(R.layout.activity_game);
        else if (gameSize == 5) setContentView(R.layout.activity_game);
        else setContentView(R.layout.activity_game);

        GameFragment gameFragment = new GameFragment();

        getFragmentManager().beginTransaction().add(R.id.game_fragment_container, gameFragment).commit();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Hide the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }
}
