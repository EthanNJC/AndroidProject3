package com.ethannjc.project3;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NewGameFragment extends Fragment {

    private Button new4Button, new5Button, new6Button, backButton;

    public NewGameFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_new_game, container, false);

        new4Button = (Button) rootView.findViewById(R.id.new_4_button);
        new5Button = (Button) rootView.findViewById(R.id.new_5_button);
        new6Button = (Button) rootView.findViewById(R.id.new_6_button);
        backButton = (Button) rootView.findViewById(R.id.back_button);

        initButtons();
        return rootView;
    }

    public void initButtons() {
        new4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("EXTRA_GAMESIZE", 4);
                startActivity(intent);
            }
        });
        new5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("EXTRA_GAMESIZE", 5);
                startActivity(intent);
            }
        });
        new6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("EXTRA_GAMESIZE", 6);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.menu_fragment_container, ((MenuActivity) getActivity()).mainMenuFragment)
                        .commit();
            }
        });

    }

}
