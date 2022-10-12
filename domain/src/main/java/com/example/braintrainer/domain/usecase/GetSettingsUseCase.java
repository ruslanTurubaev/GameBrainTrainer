package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.repository.SettingsRepositoryInterface;

public class GetSettingsUseCase {
    private SettingsRepositoryInterface settingsRepository;

    public GetSettingsUseCase(SettingsRepositoryInterface settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public SettingsDomainModel execute(){
        return settingsRepository.getSettings();
    }
}
