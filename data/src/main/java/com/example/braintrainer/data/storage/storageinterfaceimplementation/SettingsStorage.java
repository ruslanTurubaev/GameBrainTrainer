package com.example.braintrainer.data.storage.storageinterfaceimplementation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.data.storage.models.SettingsStorageModel;
import com.example.braintrainer.data.storage.storageinterface.SettingsStorageInterface;


public class SettingsStorage implements SettingsStorageInterface {
    private Context context;
    private SharedPreferences sharedPreferences;

    public SettingsStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void setSettings(SettingsStorageModel settingsStorageModel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.GAME_MODE, settingsStorageModel.getGameMode());
        editor.putInt(Constants.MODE_VALUE_INDEX, settingsStorageModel.getModeValueIndex());
        editor.apply();
    }

    @Override
    public SettingsStorageModel getSettings() {
        String gameMode = sharedPreferences.getString(Constants.GAME_MODE, Constants.GAME_MODE_1);
        int index = sharedPreferences.getInt(Constants.MODE_VALUE_INDEX, 0);
        return new SettingsStorageModel(gameMode, index);
    }
}
