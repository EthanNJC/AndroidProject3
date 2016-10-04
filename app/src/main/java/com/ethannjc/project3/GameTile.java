package com.ethannjc.project3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.TimerTask;


public class GameTile extends ImageButton {

    private ObjectAnimator hideAnimation, revealAnimation;
    private Paint paint;
    private LinearGradient coverGradient;
    private int coverAlpha;
    public int id;
    public boolean hasMatched, isHidden;

    public GameTile(Context context, final int id) {
        super(context);

        this.id = id;
        isHidden = true;
        coverAlpha = 255;
        paint = new Paint();

        // ASyncTask for lading image
        new LoadImageTask().execute(this);

        // Fade Animations
        hideAnimation = ObjectAnimator.ofInt(this, "coverAlpha", 0, 255);
        hideAnimation.setDuration(300);
        hideAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        revealAnimation = ObjectAnimator.ofInt(this, "coverAlpha", 255, 0);
        revealAnimation.setDuration(300);
        revealAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHidden) {
                    reveal();
                    if (GameFragment.frag.firstTile != null) {
                        if (GameFragment.frag.firstTile.id == id) {
                            GameFragment.frag.firstTile = null;
                            GameFragment.frag.addPair();
                        } else {
                            GameFragment.frag.disableClicks();
                            GameFragment.frag.handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    GameFragment.frag.enableClicks();
                                    GameFragment.frag.firstTile.hide();
                                    GameFragment.frag.firstTile = null;
                                    GameTile.this.hide();
                                    GameFragment.frag.addPoint();
                                }
                            }, 1000);
                        }
                    } else {
                        GameFragment.frag.firstTile = GameTile.this;
                    }
                }
            }
        });
    }

    public void reveal() {
        isHidden = false;
        revealAnimation.start();
    }

    public void hide() {
        isHidden = true;
        hideAnimation.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        coverGradient = new LinearGradient(12, 12, 12, getHeight()-12, Color.argb(coverAlpha, 199, 249, 151), Color.argb(coverAlpha, 132, 197, 54), Shader.TileMode.CLAMP);
        paint.setShader(coverGradient);
        canvas.drawRect(new RectF(12, 12, getWidth()-12, getHeight()-12), paint);
    }

    public void setCoverAlpha(int a) {
        coverAlpha = a;
    }
}
