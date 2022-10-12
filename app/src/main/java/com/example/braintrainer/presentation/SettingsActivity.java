package com.example.braintrainer.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.braintrainer.R;
import com.example.braintrainer.data.storage.constants.Constants;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsActivity extends AppCompatActivity {
    private ChipGroup chipGroup;
    private Chip chipCount, chipTime;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private CurtainView curtain;
    private boolean isGameMode1 = true;
    private boolean isButtonsBlocked = false;

    private SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    private void init(){
        chipGroup = findViewById(R.id.chip_group);
        chipCount = findViewById(R.id.chip_count);
        chipTime = findViewById(R.id.chip_time);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        curtain = findViewById(R.id.curtain);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        settingsViewModel.getIsChipAnswersChecked().observe(this, aBoolean -> chipCount.setChecked(aBoolean));
        settingsViewModel.getIsChipTimeChecked().observe(this, aBoolean -> chipTime.setChecked(aBoolean));
        settingsViewModel.getIsRadioButton1Checked().observe(this, aBoolean -> radioButton1.setChecked(aBoolean));
        settingsViewModel.getIsRadioButton2Checked().observe(this, aBoolean -> radioButton2.setChecked(aBoolean));
        settingsViewModel.getIsRadioButton3Checked().observe(this, aBoolean -> radioButton3.setChecked(aBoolean));
        settingsViewModel.getRadioButtonTex1().observe(this, integer -> radioButton1.setText(integer));
        settingsViewModel.getRadioButtonTex2().observe(this, integer -> radioButton2.setText(integer));
        settingsViewModel.getRadioButtonTex3().observe(this, integer -> radioButton3.setText(integer));

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                switch (checkedId){
                    case (R.id.chip_count):
                        settingsViewModel.setGameMode(Constants.GAME_MODE_1);
                        isGameMode1 = true;
                        break;
                    case (R.id.chip_time):
                        settingsViewModel.setGameMode(Constants.GAME_MODE_2);
                        isGameMode1 = false;
                        break;
                    default:
                        if(isGameMode1){
                            settingsViewModel.setGameMode(Constants.GAME_MODE_2);
                        }
                        else {
                            settingsViewModel.setGameMode(Constants.GAME_MODE_1);
                        }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case (R.id.radioButton1):
                        settingsViewModel.setModeValueIndex(0);
                        break;
                    case (R.id.radioButton2):
                        settingsViewModel.setModeValueIndex(1);
                        break;
                    case (R.id.radioButton3):
                        settingsViewModel.setModeValueIndex(2);
                }
            }
        });
    }

    public void onClickErase(View view) {
        settingsViewModel.eraseAllRecords();
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
}
