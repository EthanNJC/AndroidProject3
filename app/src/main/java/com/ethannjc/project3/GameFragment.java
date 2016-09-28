package com.ethannjc.project3;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameFragment extends Fragment {

    GridLayout gameGrid;
    View clickBlocker;

    public GameFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generateGrid((int) getArguments().get("EXTRA_GAMESIZE"));
        FrameLayout fl = (FrameLayout) getActivity().findViewById(R.id.game_container);
        Toast.makeText(getContext(), "Toast " + fl.getWidth(), Toast.LENGTH_SHORT).show();
        clickBlocker = new View(fl.getContext());
        clickBlocker.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        clickBlocker.requestFocus();
        clickBlocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
            }
        });
        clickBlocker.requestFocusFromTouch();
        fl.addView(clickBlocker);

    }


    public void generateGrid(int size) {
        gameGrid = (GridLayout) getActivity().findViewById(R.id.game_grid);
        gameGrid.setColumnCount(size);
        gameGrid.setRowCount(size);

        ArrayList<GameTile> tiles = new ArrayList<>();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(275-(size*25),275-(size*25));

        for (int a = 0; a < (size*size); a++) {
            int tileID = (a-(a%2))/2;
            GameTile tile = new GameTile(getContext(), tileID);

            // Take time to reflect
            try { tile.setImageResource(R.drawable.class.getField("shape" + Integer.toString(tileID)).getInt(null)); }
            catch (Exception e) { Toast.makeText(getContext(), "Error Loading Images", Toast.LENGTH_LONG).show(); }

            tile.setLayoutParams(lp);
            tile.setScaleType(ImageView.ScaleType.FIT_XY);
            tile.invalidate();
            tiles.add(tile);
        }

        Random r = new Random();
        for (int a = size*size; a > 0; a--) {
            int b = r.nextInt(a);
            gameGrid.addView(tiles.get(b), lp);
            tiles.remove(b);
        }

        //View clickBlocker = getActivity().findViewById(R.id.click_blocker);
        gameGrid.setClickable(false);
        gameGrid.setEnabled(false);
        gameGrid.setFocusableInTouchMode(false);
        gameGrid.invalidate();

    }

}
