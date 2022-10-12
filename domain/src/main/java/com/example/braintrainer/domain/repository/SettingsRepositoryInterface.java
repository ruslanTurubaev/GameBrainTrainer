package com.example.braintrainer.domain.repository;

import com.example.braintrainer.domain.models.SettingsDomainModel;

public interface SettingsRepositoryInterface {
    void setSettings(SettingsDomainModel settingsDomainModel);

    SettingsDomainModel getSettings();
}
