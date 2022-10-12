package com.example.braintrainer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.domain.models.GeneratedStepDomainModel;
import com.example.braintrainer.domain.models.ResultDomainModel;
import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.usecase.GenerateNextStepUseCase;
import com.example.braintrainer.domain.usecase.GetSettingsUseCase;
import com.example.braintrainer.domain.usecase.SetResultUseCase;

import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private GetSettingsUseCase getSettingsUseCase;
    private GenerateNextStepUseCase generateNextStepUseCase;
    private SetResultUseCase setResultUseCase;

    private int maxTrueAnswers, maxTime;
    private int score = 0;
    private int seconds = -1;
    private boolean isGameMode1, isTrueAnswer;

    private MutableLiveData<String> timerLiveData = new MutableLiveData<>();
    private MutableLiveData<String> scoreLiveData = new MutableLiveData<>("0");
    private MutableLiveData<String> expressionLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isGameResumedLiveData = new MutableLiveData<>(true);

    public LiveData<String> getTimerLiveData() {
        return timerLiveData;
    }

    public LiveData<String> getScoreLiveData() {
        return scoreLiveData;
    }

    public LiveData<String> getExpressionLiveData() {
        return expressionLiveData;
    }

    public LiveData<Boolean> getIsGameResumedLiveData() {
        return isGameResumedLiveData;
    }

    @Inject
    public MainViewModel(GetSettingsUseCase getSettingsUseCase, GenerateNextStepUseCase generateNextStepUseCase, SetResultUseCase setResultUseCase) {
        this.getSettingsUseCase = getSettingsUseCase;
        this.generateNextStepUseCase = generateNextStepUseCase;
        this.setResultUseCase = setResultUseCase;

        getSettings();
        generateNextStep();
        runTimer();
    }

    public void nextStep(boolean isUserChooseTrue){
        if((isUserChooseTrue && isTrueAnswer) || (!isUserChooseTrue && !isTrueAnswer)) {
            score++;
            scoreLiveData.setValue(Integer.toString(score));
        }

        if(isGameResume()){
            generateNextStep();
        }
    }

    private boolean isGameResume(){
        if((!isGameMode1 && seconds == maxTime) || (isGameMode1 && score == maxTrueAnswers)){
            int result;
            if(isGameMode1){
                result = seconds;
            }
            else {
                result = score;
            }
            ResultDomainModel resultDomainModel = new ResultDomainModel(result);
            setResultUseCase.execute(resultDomainModel);
            isGameResumedLiveData.postValue(false);
        }
        return isGameResumedLiveData.getValue();
    }

    private void generateNextStep(){
        GeneratedStepDomainModel generatedStepDomainModel = generateNextStepUseCase.execute();
        expressionLiveData.setValue(generatedStepDomainModel.getArithmeticalExpression());
        isTrueAnswer = generatedStepDomainModel.isCorrectExpression();
    }

    private void getSettings(){
        SettingsDomainModel settings = getSettingsUseCase.execute();
        String gameMode = settings.getGameMode();
        int modeValueIndex = settings.getModeValueIndex();
        isGameMode1 = gameMode.equals(Constants.GAME_MODE_1);

        if(isGameMode1){
            switch (modeValueIndex){
                case 0:
                    maxTrueAnswers = 10;
                    break;
                case 1:
                    maxTrueAnswers = 25;
                    break;
                case 2:
                    maxTrueAnswers = 50;
            }
        }
        else {
            switch (modeValueIndex){
                case 0:
                    maxTime = 10;
                    break;
                case 1:
                    maxTime = 30;
                    break;
                case 2:
                    maxTime = 60;
            }
        }
    }

    private void runTimer(){
         new Thread(new Runnable() {
            @Override
            public void run() {
                while (isGameResume()) {
                    if(!isGameMode1 && seconds == maxTime) break;
                    seconds++;
                    int min = seconds / 60;
                    int sec = seconds % 60;
                    String timer = String.format(Locale.ENGLISH, "%02d:%02d", min, sec);
                    timerLiveData.postValue(timer);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        isGameResumedLiveData.postValue(false);
                    }
                }
            }
        }).start();
    }
}
