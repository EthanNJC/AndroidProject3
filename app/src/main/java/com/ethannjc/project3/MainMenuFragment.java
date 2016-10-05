package com.ethannjc.project3;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuFragment extends Fragment {

    private Button newGameButton, optionsButton, exitButton;


    public MainMenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        newGameButton = (Button) view.findViewById(R.id.new_game_button);
        optionsButton = (Button) view.findViewById(R.id.options_button);
        exitButton = (Button) view.findViewById(R.id.exit_button);
        initButtons();
    }


    private void initButtons() {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenuFragment.this.getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.menu_fragment_container, new NewGameFragment())
                        .commit();
            }
        });
    }

    @Override
    public void onCreate(Bundle instance) {
        super.onCreate(instance);
    }

}
