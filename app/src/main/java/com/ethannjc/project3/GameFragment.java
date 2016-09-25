package com.ethannjc.project3;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GameFragment extends Fragment {

    public GameFragment() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_4x4, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayout gl = (GridLayout) getActivity().findViewById(R.id.game_grid);

        gl.setColumnCount(3);
        gl.setRowCount(4);
        for (int i = 0; i < 16; i++) {
            ImageButton test = new ImageButton(getContext());
            test.setImageResource(R.drawable.burned);
            test.setTag(i);
            test.setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(100,100);
            test.setLayoutParams(lp);
            test.invalidate();
            test.getLayoutParams().height = 100;
            test.getLayoutParams().width = 100;

            test.setScaleType(ImageView.ScaleType.FIT_XY);

            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.LayoutParams gp = new GridLayout.LayoutParams(rowSpan, colspan);
            gl.addView(test, gp);
        }



    }

}
