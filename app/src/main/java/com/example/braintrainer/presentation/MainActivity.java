package com.example.braintrainer.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.braintrainer.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private TextView tvScore, tvMain, tvTimer;
    private CurtainView curtain;
    private MainViewModel mainViewModel;
    private boolean isFree = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_timer);
        tvMain = findViewById(R.id.tv_main);
        curtain = findViewById(R.id.curtain);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getTimerLiveData().observe(this, timer -> tvTimer.setText(timer));
        mainViewModel.getScoreLiveData().observe(this, score -> tvScore.setText(score));
        mainViewModel.getExpressionLiveData().observe(this, expression -> tvMain.setText(expression));
        mainViewModel.getIsGameResumedLiveData().observe(this, isGameResume -> {
            if(!isGameResume && isFree){
                isFree = false;
                runTransaction();
            }
        });
    }

    public void onClickAnswerButton(View view) {
        runAnimation(view);
        int id = view.getId();
        mainViewModel.nextStep(id == R.id.button3);
    }

    private void runAnimation (View view){
        AnimatorSet animationSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.scale_animation);
        animationSet.setTarget(view);
        animationSet.start();
    }

    private void runTransaction(){
        Handler animationHandler = new Handler();
            curtain.runAnimation();
            animationHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                    startActivity(intent);
                }
            }, curtain.getAnimationDuration());
    }
}