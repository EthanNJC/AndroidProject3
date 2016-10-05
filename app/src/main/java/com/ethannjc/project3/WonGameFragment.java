package com.ethannjc.project3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WonGameFragment extends Fragment {

    private Button restartButton, mainMenuButton;


    public WonGameFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_won_game, container, false);

        restartButton = (Button) rootView.findViewById(R.id.restart_game_button);
        mainMenuButton = (Button) rootView.findViewById(R.id.main_menu_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = (int) getArguments().get("EXTRA_GAMESIZE");
                Bundle b = new Bundle();
                b.putInt("EXTRA_GAMESIZE", size);
                GameFragment gameFragment = new GameFragment();
                gameFragment.setArguments(b);

                getActivity().getFragmentManager().beginTransaction().remove(WonGameFragment.this).commit();
                getActivity().getFragmentManager().beginTransaction().add(R.id.game_fragment_container, gameFragment).commit();
            }
        });
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
            }
        });

        return rootView;
    }

}
