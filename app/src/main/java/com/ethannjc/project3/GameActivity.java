package com.ethannjc.project3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    static final String FRAG_TAG = "gameFragment";
    GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Either find the existing gameFragment or make a new one
        if (savedInstanceState != null) {
            gameFragment = (GameFragment) getFragmentManager().findFragmentByTag(FRAG_TAG);
            Log.d("Project 3", "Fragment Reloaded: " + (gameFragment != null));
            Toast.makeText(this, "Where, oh where, has my game fragment gone?", Toast.LENGTH_LONG).show();

            // I read the documentation on replace(), add(), remove() and tried varieties of each, no dice
            getFragmentManager().beginTransaction().remove(gameFragment).commit();
        } else {
            gameFragment = new GameFragment();
            // Pass along the grid size from the NewGameFragment to the GameFragment
            Bundle b = new Bundle();
            int gameSize = getIntent().getIntExtra("EXTRA_GAMESIZE", 4);
            b.putInt("EXTRA_GAMESIZE", gameSize);
            gameFragment.setArguments(b);
        }

        // Commit the GameFragment
        getFragmentManager().beginTransaction().add(R.id.game_fragment_container, gameFragment, FRAG_TAG).commit();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Hide the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }
}
