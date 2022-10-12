package com.example.braintrainer.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.braintrainer.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FinalActivity  extends AppCompatActivity {

    private boolean isButtonsBlocked = false;
    private TextView tvTitle, tvScore, tvBestScore;
    private CurtainView curtain;

    private FinalViewModel finalViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        tvTitle = findViewById(R.id.tv_title);
        tvScore = findViewById(R.id.tv_final_score);
        tvBestScore = findViewById(R.id.tv_best_score);
        curtain = findViewById(R.id.curtain);

        finalViewModel = new ViewModelProvider(this).get(FinalViewModel.class);

        finalViewModel.getTitleLiveData().observe(this, title -> tvTitle.setText(title));
        finalViewModel.getRecordLiveData().observe(this, record -> tvBestScore.setText(record));
        finalViewModel.getResultLiveData().observe(this, result -> tvScore.setText(result));
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
            finalViewModel.eraseRecord();
            Toast.makeText(this, "Record has been erased", Toast.LENGTH_SHORT).show();
        }
    }
}
