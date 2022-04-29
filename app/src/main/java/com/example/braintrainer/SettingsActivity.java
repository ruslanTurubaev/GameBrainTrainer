package com.example.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SettingsActivity extends Activity {
    private ChipGroup chipGroup;
    private Chip chipCount, chipTime;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private CurtainView curtain;
    private SharedPreferences sharedPreferences;
    private boolean isGameMode1 = true;
    private boolean isButtonsBlocked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init(){
        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);
        chipGroup = findViewById(R.id.chip_group);
        chipCount = findViewById(R.id.chip_count);
        chipTime = findViewById(R.id.chip_time);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        curtain = findViewById(R.id.curtain);

        String gameMode = sharedPreferences.getString(Constants.GAME_MODE, Constants.GAME_MODE_1);
        int index = sharedPreferences.getInt(Constants.MODE_VALUE_INDEX, 0);
        setCheckedRadioButton(index);

        if (gameMode.equals(Constants.GAME_MODE_1)){
            chipCount.setChecked(true);
            setRadioButtonsText(true);
        }
        else {
            chipTime.setChecked(true);
            setRadioButtonsText(false);
        }

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                switch (checkedId){
                    case (R.id.chip_count):
                        setRadioButtonsText(true);
                        editor.putString(Constants.GAME_MODE, Constants.GAME_MODE_1);
                        isGameMode1 = true;
                        break;
                    case (R.id.chip_time):
                        setRadioButtonsText(false);
                        editor.putString(Constants.GAME_MODE, Constants.GAME_MODE_2);
                        isGameMode1 = false;
                        break;
                    default:
                        if(isGameMode1){
                            chipTime.setChecked(true);
                        }
                        else {
                            chipCount.setChecked(true);
                        }
                }
                editor.apply();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int index = 0;
                switch (i){
                    case (R.id.radioButton1):
                        index = 0;
                        break;
                    case (R.id.radioButton2):
                        index = 1;
                        break;
                    case (R.id.radioButton3):
                        index = 2;
                        break;
                    default:
                        index = 0;
                        radioGroup.check(R.id.radioButton1);
                }
                editor.putInt(Constants.MODE_VALUE_INDEX, index);
                editor.apply();
            }
        });
    }

    public void onClickErase(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.ANSWERS_10_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.ANSWERS_25_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.ANSWERS_50_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.TIME_10_RECORD, 0);
        editor.putInt(Constants.TIME_30_RECORD, 0);
        editor.putInt(Constants.TIME_60_RECORD, 0);
        editor.apply();

        Toast.makeText(this, "All records have been erased", Toast.LENGTH_SHORT).show();
    }

    public void onClickBackButton(View view) {
        if(!isButtonsBlocked) {
            isButtonsBlocked = true;
            Handler handler = new Handler();
            curtain.runAnimation();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SettingsActivity.this, StartActivity.class);
                    startActivity(intent);
                }
            }, curtain.getAnimationDuration());
        }

    }

    private void setRadioButtonsText(boolean isGameMode1){
        if (isGameMode1){
            radioButton1.setText(R.string.answer1);
            radioButton2.setText(R.string.answer2);
            radioButton3.setText(R.string.answer3);
        }
        else {
            radioButton1.setText(R.string.time1);
            radioButton2.setText(R.string.time2);
            radioButton3.setText(R.string.time3);
        }
    }

    private void setCheckedRadioButton(int index){
        switch (index){
            case 0:
                radioButton1.setChecked(true);
                break;
            case 1:
                radioButton2.setChecked(true);
                break;
            case 2:
                radioButton3.setChecked(true);
        }
    }
}
