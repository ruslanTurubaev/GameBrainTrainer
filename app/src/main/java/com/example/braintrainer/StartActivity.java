package com.example.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

public class StartActivity extends Activity {
    private boolean isButtonsBlocked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void runTransactionAnimation(Intent intent) {
        CurtainView curtain = findViewById(R.id.curtain);
        Handler handler = new Handler();

        curtain.runAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, curtain.getAnimationDuration());
    }

    public void onClickStart(View view) {
        if(!isButtonsBlocked) {
            isButtonsBlocked = true;
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            runTransactionAnimation(intent);
        }
    }

    public void onClickSettings(View view) {
        if(!isButtonsBlocked) {
            isButtonsBlocked = true;
            Intent intent = new Intent(StartActivity.this, SettingsActivity.class);
            runTransactionAnimation(intent);
        }
    }
}
