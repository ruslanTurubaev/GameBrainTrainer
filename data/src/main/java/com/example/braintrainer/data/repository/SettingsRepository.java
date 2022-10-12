package com.example.braintrainer.data.repository;

import com.example.braintrainer.data.storage.models.SettingsStorageModel;
import com.example.braintrainer.data.storage.storageinterface.SettingsStorageInterface;
import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.repository.SettingsRepositoryInterface;

public class SettingsRepository implements SettingsRepositoryInterface {
    SettingsStorageInterface settingsStorage;

    public SettingsRepository(SettingsStorageInterface settingsStorage) {
        this.settingsStorage = settingsStorage;
    }

    @Override
    public void setSettings(SettingsDomainModel settingsDomainModel) {
        SettingsStorageModel settingsStorageModel = mapToStorage(settingsDomainModel);
        settingsStorage.setSettings(settingsStorageModel);
    }

    @Override
    public SettingsDomainModel getSettings() {
        SettingsStorageModel settingsStorageModel = settingsStorage.getSettings();
        return mapToDomain(settingsStorageModel);
    }

    private SettingsStorageModel mapToStorage(SettingsDomainModel settingsDomainModel){
        return new SettingsStorageModel(settingsDomainModel.getGameMode(), settingsDomainModel.getModeValueIndex());
    }

    private SettingsDomainModel mapToDomain(SettingsStorageModel settingsStorageModel){
        return new SettingsDomainModel(settingsStorageModel.getGameMode(), settingsStorageModel.getModeValueIndex());
    }
}
