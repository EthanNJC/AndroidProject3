package com.ethannjc.project3;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameFragment extends Fragment {

    public GameFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generateGrid((int) getArguments().get("EXTRA_GAMESIZE"));
    }

    public void generateGrid(int size) {
        GridLayout gl = (GridLayout) getActivity().findViewById(R.id.game_grid);
        gl.setColumnCount(size);
        gl.setRowCount(size);
        GameTile[] tiles = new GameTile[size*size];

        for (int a = 0; a < (size*size); a++) {
            GameTile tile = new GameTile(getContext(), (a-(a%2))/2);

            // Take time to reflect
            try { tile.setImageResource(R.drawable.class.getField("shape" + Integer.toString((a-(a%2))/2)).getInt(null)); }
            catch (Exception e) { Toast.makeText(getContext(), "Error Loading Images", Toast.LENGTH_LONG).show(); }


            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(275-(size*25),275-(size*25));
            tile.setLayoutParams(lp);
            tile.setScaleType(ImageView.ScaleType.FIT_XY);
            tile.invalidate();
            gl.addView(tile, lp);
        }
    }

}
