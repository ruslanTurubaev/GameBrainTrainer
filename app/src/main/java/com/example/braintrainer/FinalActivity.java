package com.example.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Locale;

public class FinalActivity  extends Activity {

    private SharedPreferences sharedPreferences;
    private boolean isGameMode1;
    private boolean isButtonsBlocked = false;
    private String recordFlag;
    private TextView tvTitle, tvScore, tvBestScore;
    private CurtainView curtain;
    private int result;
    private int bestResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        init();
    }

    private void init(){
        tvTitle = findViewById(R.id.tv_title);
        tvScore = findViewById(R.id.tv_final_score);
        tvBestScore = findViewById(R.id.tv_best_score);
        curtain = findViewById(R.id.curtain);

        Intent intent = getIntent();

        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);
        isGameMode1 = sharedPreferences.getString(Constants.GAME_MODE, Constants.GAME_MODE_1).equals(Constants.GAME_MODE_1);
        int index = sharedPreferences.getInt(Constants.MODE_VALUE_INDEX, 0);

        if(isGameMode1){
            switch (index){
                case 0:
                    recordFlag = Constants.ANSWERS_10_RECORD;
                    break;
                case 1:
                    recordFlag = Constants.ANSWERS_25_RECORD;
                    break;
                case 3:
                    recordFlag = Constants.ANSWERS_50_RECORD;
            }

            result = intent.getIntExtra(Constants.CURRENT_SCORE, Integer.MAX_VALUE);
            bestResult = sharedPreferences.getInt(recordFlag, Integer.MAX_VALUE);
        }
        else {
            switch (index){
                case 0:
                    recordFlag = Constants.TIME_10_RECORD;
                    break;
                case 1:
                    recordFlag = Constants.TIME_30_RECORD;
                    break;
                case 3:
                    recordFlag = Constants.TIME_60_RECORD;
            }

            result = intent.getIntExtra(Constants.CURRENT_SCORE, 0);
            bestResult = sharedPreferences.getInt(recordFlag, 0);
        }

        setScore();
    }

    private void setScore(){
        if (isGameMode1 && result < bestResult) {
            setNewRecord();
        }

        if (!isGameMode1 && result > bestResult) {
            setNewRecord();
        }

        displayScore();
    }

    private void setNewRecord(){
        tvTitle.setText(R.string.new_record);
        bestResult = result;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(recordFlag, bestResult);
        editor.apply();
    }

    private void displayScore(){
        if(isGameMode1){
            int min = result /60;
            int sec = result % 60;
            String resultLyne = String.format(Locale.ENGLISH, "%02d:%02d", min, sec);
            tvScore.setText(resultLyne);

            min = bestResult / 60;
            sec = bestResult % 60;
            resultLyne = String.format(Locale.ENGLISH, "%02d:%02d", min, sec);
            tvBestScore.setText(resultLyne);
        }
        else {
            tvScore.setText(String.valueOf(result));
            tvBestScore.setText(String.valueOf(bestResult));
        }
    }

    public void onClickBack(View view) {
        isButtonsBlocked = true;
        Handler handler = new Handler();
        curtain.runAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FinalActivity.this, StartActivity.class);
                startActivity(intent);
            }
        }, curtain.getAnimationDuration());
    }

    public void onClickEraseRecord(View view) {
        if(!isButtonsBlocked) {
            isButtonsBlocked = true;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isGameMode1) {
                editor.putInt(recordFlag, Integer.MAX_VALUE);
            } else {
                editor.putInt(recordFlag, 0);

            }
            editor.apply();
            Toast.makeText(this, "Record has been erased", Toast.LENGTH_SHORT).show();
        }
    }
}
