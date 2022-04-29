package com.example.braintrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private int number1, number2, numberResult, numberFalse, maxTrueAnswer, maxTime;
    private int seconds = 0;
    private int trueAnswer = 0;
    private boolean isTrueAnswer = false;
    private boolean isGameMode1 = true;
    private boolean isGameResumed = true;

    private TextView tvScore, tvMain, tvTimer;
    private CurtainView curtain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_timer);
        tvMain = findViewById(R.id.tv_main);
        curtain = findViewById(R.id.curtain);

        if(savedInstanceState != null){
            tvScore.setText(savedInstanceState.getCharSequence(Constants.SCORE));
            tvMain.setText(savedInstanceState.getCharSequence(Constants.EXPLANATION));
            isTrueAnswer = savedInstanceState.getBoolean(Constants.IS_TRUE_ANSWER);
            seconds = savedInstanceState.getInt(Constants.SECONDS);
            trueAnswer = savedInstanceState.getInt(Constants.TRUE_ANSWER);
        }
        else {
            nextGameStep();
        }

        init();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(Constants.SCORE, tvScore.getText());
        outState.putCharSequence(Constants.EXPLANATION, tvMain.getText());
        outState.putBoolean(Constants.IS_TRUE_ANSWER, isTrueAnswer);
        outState.putInt(Constants.SECONDS, seconds);
        outState.putInt(Constants.TRUE_ANSWER, trueAnswer);
    }

    private void init(){
        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);
        isGameMode1 = sharedPreferences.getString(Constants.GAME_MODE, Constants.GAME_MODE_1).equals(Constants.GAME_MODE_1);
        int index = sharedPreferences.getInt(Constants.MODE_VALUE_INDEX, 0);

        if(isGameMode1){
            switch (index){
                case 0:
                    maxTrueAnswer = 10;
                    break;
                case 1:
                    maxTrueAnswer = 25;
                    break;
                case 2:
                    maxTrueAnswer = 50;
            }
        }
        else {
            switch (index){
                case 0:
                    maxTime = 10;
                    break;
                case 1:
                    maxTime = 30;
                    break;
                case 2:
                    maxTime = 60;
            }
        }

        runTimer();
    }

    public void onClickAnswerButton(View view) {
        runAnimation(view);
        int id = view.getId();
        if(isGameResumed){
            if(isTrueAnswer && id == R.id.button3) trueAnswer++;
            if(!isTrueAnswer && id == R.id.button4) trueAnswer++;
            isGameEnded();
        }
    }

    private void runAnimation (View view){
        AnimatorSet animationSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.scale_animation);
        animationSet.setTarget(view);
        animationSet.start();
    }

    private void nextGameStep(){
        generateNumbers();
        displayExplanation();
    }

    /*
    the method generate 4 numbers, 2 numbers generated within given range randomly - terms.
    the third number is the result of the summation of the two above numbers - sum result.
    the fourth number is generated randomly within the range from the max between the two terms to the sum result.
     */
    private void generateNumbers(){
       int min = 3;
       int max = 20;
       int min_generated_number;

       number1 = (int) (Math.random() * (max - min) + min);
       number2 = (int) (Math.random() * (max - min) + min);

        min_generated_number = Math.max(number1, number2);

        numberResult = number1 + number2;
        numberFalse = (int) (Math.random() * (numberResult - min_generated_number) + min_generated_number);
    }

    private void displayExplanation(){
        String explanation;
        int choose = (int) (Math.random() * 2);
        if(choose == 1){
            explanation = number1 + " + " + number2 + " = " + numberResult;
            isTrueAnswer = true;
        }
        else {
            explanation = number1 + " + " + number2 + " = " + numberFalse;
            isTrueAnswer = false;
        }
        tvMain.setText(explanation);
    }

    private void displayScore(){
        tvScore.setText(String.valueOf(trueAnswer));
    }

    private void isGameEnded(){
        displayScore();
        if((!isGameMode1 && seconds == maxTime) || (isGameMode1 && trueAnswer == maxTrueAnswer)){
            isGameResumed = false;
            Handler animationHandler = new Handler();
            curtain.runAnimation();
            animationHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                    if(isGameMode1){
                        intent.putExtra(Constants.CURRENT_SCORE, seconds);
                    }
                    else {
                        intent.putExtra(Constants.CURRENT_SCORE, trueAnswer);
                    }
                    startActivity(intent);
                }
            }, curtain.getAnimationDuration());
        }
        else {
            nextGameStep();
        }
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isGameResumed) {
                    seconds++;
                    int min = seconds / 60;
                    int sec = seconds % 60;
                    String timer = String.format(Locale.ENGLISH, "%02d:%02d", min, sec);
                    tvTimer.setText(timer);
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}