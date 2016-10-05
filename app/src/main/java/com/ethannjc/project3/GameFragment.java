package com.ethannjc.project3;

import android.graphics.Point;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameFragment extends Fragment {

    private GridLayout gameGrid;
    private RelativeLayout gameContainer;
    private View clickBlocker;
    private TextView scoreText;
    private int score, completePairs, size;
    public Handler handler;
    public GameTile firstTile;
    public static GameFragment frag;


    public GameFragment() {
        frag = this;
        handler = new Handler();
        score = 0;
        completePairs = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null) return;
        gameContainer = (RelativeLayout) view.findViewById(R.id.game_container);
        gameContainer.addView(gameGrid);

        scoreText = (TextView) getActivity().findViewById(R.id.score_text);
        scoreText.setText("Score: " + score);
        scoreText.invalidate();

        // All of this just to stop clicks... calling setClickable for either the gameGrid or
        // by iterating through all of the tiles is useless because when a view has an onClickListener:
        // "If this view is not clickable, it becomes clickable." - Documentation
        // Have to queue this stuff because gameContainer.getWidth() is inaccurate, even during the onResume lifecycle stage
        gameContainer.post(new Runnable() {
            @Override
            public void run() {
                // Prepare a click blocking view for when a mismatch occurs(So the user
                // can see the two mismatched cards for a second without flipping others)
                clickBlocker = new View(getContext());
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(gameContainer.getWidth(), gameContainer.getWidth());
                clickBlocker.setLayoutParams(lp);
                // Can't block clicks unless it can take them
                clickBlocker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {}
                });
                gameContainer.addView(clickBlocker);
                enableClicks();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        size = (int) getArguments().get("EXTRA_GAMESIZE");
        generateGrid();
    }


    public void generateGrid() {
        // Initialize the game grid, tile arraylist, and a generic layoutParams to re-use for every generated tile
        gameGrid = new GridLayout(getContext());
        gameGrid.setColumnCount(size);
        gameGrid.setRowCount(size);
        ArrayList<GameTile> tiles = new ArrayList<>();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        int width = p.x;
        if (p.y < width) width = p.y;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((width-(width/4))/size,(width-(width/4))/size);

        // Generate necessary tiles
        for (int a = 0; a < (size*size); a++) {
            int tileID = (a-(a%2))/2;
            GameTile tile = new GameTile(getContext(), tileID);
            tile.setLayoutParams(lp);
            tile.setScaleType(ImageView.ScaleType.FIT_XY);
            tile.invalidate();
            tiles.add(tile);
        }

        // Special case for 5x5
        if (size == 5) {
            tiles.remove(24);
            GameTile emptyTile = new GameTile(getContext(), 0);
            emptyTile.setVisibility(View.GONE);
            tiles.add(emptyTile);
        }
        // Randomly distribute tiles into grid
        Random r = new Random();
        for (int a = size*size; a > 0; a--) {
            int b = r.nextInt(a);
            gameGrid.addView(tiles.get(b), lp);
            tiles.remove(b);
        }
    }


    public void addPoint() {
        score++;
        scoreText.setText("Score: " + score);
        scoreText.invalidate();
    }


    public void addPair() {
        completePairs++;
        if (completePairs == (size*size)/2) {
            Bundle b = new Bundle();
            b.putInt("EXTRA_GAMESIZE", size);
            WonGameFragment wonGameFragment = new WonGameFragment();
            wonGameFragment.setArguments(b);
            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
            getActivity().getFragmentManager().beginTransaction().add(R.id.game_fragment_container, wonGameFragment).commit();
        }
    }


    public void disableClicks() { clickBlocker.setVisibility(View.VISIBLE); }


    public void enableClicks() { clickBlocker.setVisibility(View.GONE); }

}
