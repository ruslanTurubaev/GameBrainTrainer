package com.example.braintrainer.data.storage.storageinterface;

import com.example.braintrainer.data.storage.models.SettingsStorageModel;

public interface SettingsStorageInterface {

    void setSettings(SettingsStorageModel settingsStorageModel);

    SettingsStorageModel getSettings();

}
