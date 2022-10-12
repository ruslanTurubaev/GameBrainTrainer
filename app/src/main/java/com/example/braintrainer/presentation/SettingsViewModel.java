package com.example.braintrainer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.braintrainer.R;
import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.usecase.EraseAllRecordsUseCase;
import com.example.braintrainer.domain.usecase.GetSettingsUseCase;
import com.example.braintrainer.domain.usecase.SetSettingsUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends ViewModel {
    private SetSettingsUseCase setSettingsUseCase;
    private GetSettingsUseCase getSettingsUseCase;
    private EraseAllRecordsUseCase eraseAllRecordsUseCase;

    private String gameMode;
    private int modeValueIndex;

    private MutableLiveData<Integer> radioButtonTex1 = new MutableLiveData<>();
    private MutableLiveData<Integer> radioButtonTex2 = new MutableLiveData<>();
    private MutableLiveData<Integer> radioButtonTex3 = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRadioButton1Checked = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRadioButton2Checked = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRadioButton3Checked = new MutableLiveData<>();
    private MutableLiveData<Boolean> isChipTimeChecked = new MutableLiveData<>();
    private MutableLiveData<Boolean> isChipAnswersChecked = new MutableLiveData<>();

    public LiveData<Integer> getRadioButtonTex1() {
        return radioButtonTex1;
    }

    public LiveData<Integer> getRadioButtonTex2() {
        return radioButtonTex2;
    }

    public LiveData<Integer> getRadioButtonTex3() {
        return radioButtonTex3;
    }

    public LiveData<Boolean> getIsRadioButton1Checked() {
        return isRadioButton1Checked;
    }

    public LiveData<Boolean> getIsRadioButton2Checked() {
        return isRadioButton2Checked;
    }

    public LiveData<Boolean> getIsRadioButton3Checked() {
        return isRadioButton3Checked;
    }

    public LiveData<Boolean> getIsChipTimeChecked() {
        return isChipTimeChecked;
    }

    public LiveData<Boolean> getIsChipAnswersChecked() {
        return isChipAnswersChecked;
    }

    @Inject
    public SettingsViewModel(SetSettingsUseCase setSettingsUseCase, GetSettingsUseCase getSettingsUseCase, EraseAllRecordsUseCase eraseAllRecordsUseCase) {
        this.setSettingsUseCase = setSettingsUseCase;
        this.getSettingsUseCase = getSettingsUseCase;
        this.eraseAllRecordsUseCase = eraseAllRecordsUseCase;
        getSettings();
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        setSettings();
        displaySettings();
    }

    public void setModeValueIndex(int modeValueIndex){
        this.modeValueIndex = modeValueIndex;
        setSettings();
        displaySettings();
    }

    public void getSettings(){
        SettingsDomainModel settings = getSettingsUseCase.execute();
        gameMode = settings.getGameMode();
        modeValueIndex = settings.getModeValueIndex();
        displaySettings();
    }

    public void eraseAllRecords(){
        eraseAllRecordsUseCase.execute();
    }

    private void setSettings(){
        SettingsDomainModel settings = new SettingsDomainModel(gameMode, modeValueIndex);
        setSettingsUseCase.execute(settings);
    }

    private void displaySettings(){
        setChipChecked();
        setRadioButtonsText();
        setCheckedRadioButton();
    }

    private void setChipChecked(){
        switch (gameMode){
            case Constants.GAME_MODE_1:
                isChipAnswersChecked.setValue(true);
                isChipTimeChecked.setValue(false);
                break;
            case Constants.GAME_MODE_2:
                isChipAnswersChecked.setValue(false);
                isChipTimeChecked.setValue(true);
        }
    }

    private void setRadioButtonsText(){
        if(gameMode.equals(Constants.GAME_MODE_1)){
            radioButtonTex1.setValue(R.string.answer1);
            radioButtonTex2.setValue(R.string.answer2);
            radioButtonTex3.setValue(R.string.answer3);
        }
        else {
            radioButtonTex1.setValue(R.string.time1);
            radioButtonTex2.setValue(R.string.time2);
            radioButtonTex3.setValue(R.string.time3);
        }
    }

    private void setCheckedRadioButton(){
        switch (modeValueIndex){
            case 0:
                isRadioButton1Checked.setValue(true);
                isRadioButton2Checked.setValue(false);
                isRadioButton3Checked.setValue(false);
                break;
            case 1:
                isRadioButton1Checked.setValue(false);
                isRadioButton2Checked.setValue(true);
                isRadioButton3Checked.setValue(false);
                break;
            case 2:
                isRadioButton1Checked.setValue(false);
                isRadioButton2Checked.setValue(false);
                isRadioButton3Checked.setValue(true);
        }
    }
}
