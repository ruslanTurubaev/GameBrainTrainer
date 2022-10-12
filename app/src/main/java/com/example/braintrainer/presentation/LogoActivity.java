package com.example.braintrainer.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.braintrainer.R;

public class LogoActivity extends Activity {
    private Animation alphaAnimation;
    private TextView appName;
    private ImageView logo;
    private CurtainView curtain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        init();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                curtain.runAnimation();
            }
        },  1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoActivity.this, StartActivity.class);
                startActivity(intent);
            }
        },  curtain.getAnimationDuration() + 1500);
    }

    private void init(){
        appName = findViewById(R.id.app_name);
        logo = findViewById(R.id.logo);

        alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_animation);
        appName.setAnimation(alphaAnimation);
        logo.setAnimation(alphaAnimation);
        curtain = findViewById(R.id.curtain);
    }
}
