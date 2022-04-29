package com.example.braintrainer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class CurtainView extends View {
    private static final int ANIMATION_DURATION = 700;
    private float radius = 0f;
    private Paint paint = new Paint();

    public CurtainView(Context context) {
        super(context);
    }

    public CurtainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public int getAnimationDuration(){
        return ANIMATION_DURATION;
    }

    private float getMaxRadius(){
        int x = getWidth();
        int y = getHeight();
        return (float) Math.sqrt(Math.pow(x, 2d) + Math.pow(y, 2d));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(0, getHeight(), radius, paint);
        super.onDraw(canvas);
    }

    public void runAnimation(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getMaxRadius());
        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}
