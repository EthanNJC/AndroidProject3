package com.ethannjc.project3;

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

    private Button newGameButton, resumeGameButton, optionsButton, exitButton;

    public MainMenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

        newGameButton = (Button) rootView.findViewById(R.id.new_game_button);
        resumeGameButton = (Button) rootView.findViewById(R.id.resume_game_button);
        optionsButton = (Button) rootView.findViewById(R.id.options_button);
        exitButton = (Button) rootView.findViewById(R.id.exit_button);

        initButtons();

        return rootView;
    }

    private void initButtons() {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.menu_fragment_container, ((MenuActivity) getActivity()).newGameFragment)
                        .commit();
            }
        });
    }

    @Override
    public void onCreate(Bundle instance) {
        super.onCreate(instance);
    }

}
