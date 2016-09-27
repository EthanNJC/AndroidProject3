package com.ethannjc.project3;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class GameTile extends ImageButton {

    int id;
    boolean hasMatched;

    public GameTile(Context context, final int id) {
        super(context);
        this.id = id;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), Integer.toString(id), Toast.LENGTH_LONG).show();
            }
        });
    }
}
