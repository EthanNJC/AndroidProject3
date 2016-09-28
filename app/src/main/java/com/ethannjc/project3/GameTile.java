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


public class GameTile extends ImageButton {

    ObjectAnimator hideAnimation, revealAnimation;
    Paint paint;
    LinearGradient coverGradient;
    int coverAlpha, id;
    boolean hasMatched, hidden;

    public GameTile(Context context, final int id) {
        super(context);

        this.id = id;
        hidden = true;
        coverAlpha = 255;
        paint = new Paint();

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
                //Toast.makeText(getContext(), "Clicked " + id, Toast.LENGTH_SHORT).show();
                if (hidden) reveal();
            }
        });
    }

    public void reveal() {
        hidden = false;
        revealAnimation.start();
    }

    public void hide() {
        hidden = true;
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
