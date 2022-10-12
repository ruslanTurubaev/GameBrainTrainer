package com.example.braintrainer.domain.models;

import java.util.Objects;

public class SettingsDomainModel {

    private String GameMode;
    private int modeValueIndex;

    public SettingsDomainModel(String GameMode, int modeValueIndex) {
        this.GameMode = GameMode;
        this.modeValueIndex = modeValueIndex;
    }

    public String getGameMode() {
        return GameMode;
    }

    public void setGameMode(String gameMode) {
        GameMode = gameMode;
    }

    public int getModeValueIndex() {
        return modeValueIndex;
    }

    public void setModeValueIndex(int modeValueIndex) {
        this.modeValueIndex = modeValueIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsDomainModel that = (SettingsDomainModel) o;
        return getModeValueIndex() == that.getModeValueIndex() && Objects.equals(getGameMode(), that.getGameMode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameMode(), getModeValueIndex());
    }
}
