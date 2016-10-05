package com.ethannjc.project3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public MainMenuFragment mainMenuFragment;
    public NewGameFragment newGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (savedInstanceState != null) return;

        mainMenuFragment = new MainMenuFragment();

        getFragmentManager().beginTransaction().add(R.id.menu_fragment_container, mainMenuFragment).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Hide the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }

}
