package com.ethannjc.project3;


import android.os.AsyncTask;
import android.widget.Toast;

public class LoadImageTask extends AsyncTask<GameTile, Void, Integer> {

    GameTile tile;

    @Override
    protected Integer doInBackground(GameTile... tiles) {
        int resourceID;
        tile = tiles[0];

        // Reflection! (faster than getResources.getIdentifier() )
        try { resourceID = (R.drawable.class.getField("shape" + tile.id)).getInt(null); }
        catch (Exception e) { resourceID = -1; }

        return resourceID;
    }

    @Override
    protected void onPostExecute(Integer i) {
        if (i == -1) Toast.makeText(tile.getContext(), "Error Loading Image: shape" + tile.id, Toast.LENGTH_LONG).show();
        else tile.setImageResource(i);
    }
}
