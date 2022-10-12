package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.repository.SettingsRepositoryInterface;

public class SetSettingsUseCase {
    private SettingsRepositoryInterface settingsRepository;

    public SetSettingsUseCase(SettingsRepositoryInterface settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void execute(SettingsDomainModel settingsDomainModel){
        settingsRepository.setSettings(settingsDomainModel);
    }
}
