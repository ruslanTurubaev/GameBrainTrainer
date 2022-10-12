package com.example.braintrainer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.braintrainer.R;
import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.domain.models.RecordDomainModel;
import com.example.braintrainer.domain.models.RecordsFlagDomainModel;
import com.example.braintrainer.domain.models.RecordsTypeForEraseDomainModel;
import com.example.braintrainer.domain.models.ResultDefaultValueDomainModel;
import com.example.braintrainer.domain.models.ResultDomainModel;
import com.example.braintrainer.domain.models.SettingsDomainModel;
import com.example.braintrainer.domain.usecase.EraseRecordUseCase;
import com.example.braintrainer.domain.usecase.GetRecordsUseCase;
import com.example.braintrainer.domain.usecase.GetResultUseCase;
import com.example.braintrainer.domain.usecase.GetSettingsUseCase;
import com.example.braintrainer.domain.usecase.SetRecordUseCase;

import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FinalViewModel extends ViewModel {
    private EraseRecordUseCase eraseRecordUseCase;
    private GetResultUseCase getResultUseCase;
    private GetRecordsUseCase getRecordsUseCase;
    private GetSettingsUseCase getSettingsUseCase;
    private SetRecordUseCase setRecordUseCase;

    private String flag;
    private boolean isGameMode1;
    private int record, defaultValue, result;

    private MutableLiveData<Integer> titleLiveData = new MutableLiveData<>();
    private MutableLiveData<String> resultLiveData = new MutableLiveData<>();
    private MutableLiveData<String> recordLiveData = new MutableLiveData<>();

    public LiveData<Integer> getTitleLiveData() {
        return titleLiveData;
    }

    public LiveData<String> getResultLiveData() {
        return resultLiveData;
    }

    public LiveData<String> getRecordLiveData() {
        return recordLiveData;
    }

    @Inject
    public FinalViewModel(EraseRecordUseCase eraseRecordUseCase, GetResultUseCase getResultUseCase, GetRecordsUseCase getRecordsUseCase, GetSettingsUseCase getSettingsUseCase, SetRecordUseCase setRecordUseCase) {
        this.eraseRecordUseCase = eraseRecordUseCase;
        this.getResultUseCase = getResultUseCase;
        this.getRecordsUseCase = getRecordsUseCase;
        this.getSettingsUseCase = getSettingsUseCase;
        this.setRecordUseCase = setRecordUseCase;

        getSettings();
        getRecord();
        getResult();
        isRecord();
        setTitles();
    }

    public void eraseRecord(){
        RecordsTypeForEraseDomainModel recordsTypeForEraseDomainModel = new RecordsTypeForEraseDomainModel(flag);
        eraseRecordUseCase.execute(recordsTypeForEraseDomainModel);
    }

    private void getSettings(){
        SettingsDomainModel settingsDomainModel = getSettingsUseCase.execute();
        String gameMode = settingsDomainModel.getGameMode();
        int modeValueIndex = settingsDomainModel.getModeValueIndex();
        isGameMode1 = gameMode.equals(Constants.GAME_MODE_1);

        if(isGameMode1){
            defaultValue = Integer.MAX_VALUE;
            switch (modeValueIndex){
                case 0:
                    flag = Constants.ANSWERS_10_RECORD;
                    break;
                case 1:
                    flag = Constants.ANSWERS_25_RECORD;
                    break;
                case 2:
                    flag = Constants.ANSWERS_50_RECORD;
            }
        }
        else {
            defaultValue = 0;
            switch (modeValueIndex){
                case 0:
                    flag = Constants.TIME_10_RECORD;
                    break;
                case 1:
                    flag = Constants.TIME_30_RECORD;
                    break;
                case 2:
                    flag = Constants.TIME_60_RECORD;
            }
        }
    }

    private void getRecord(){
        RecordsFlagDomainModel recordsFlagDomainModel = new RecordsFlagDomainModel(flag);
        RecordDomainModel recordDomainModel = getRecordsUseCase.execute(recordsFlagDomainModel);
        record = recordDomainModel.getRecord();
    }

    private void getResult(){
        ResultDefaultValueDomainModel resultDefaultValueDomainModel = new ResultDefaultValueDomainModel(defaultValue);
        ResultDomainModel resultDomainModel = getResultUseCase.execute(resultDefaultValueDomainModel);
        result = resultDomainModel.getResult();
    }

    private void isRecord(){
        if(isGameMode1 && result < record || !isGameMode1 && result > record){
            titleLiveData.setValue(R.string.new_record);
            record = result;
            RecordDomainModel recordDomainModel = new RecordDomainModel(flag, result);
            setRecordUseCase.execute(recordDomainModel);
        }
        else {
            titleLiveData.setValue(R.string.final_activity_title);
        }
    }

    private void setTitles(){
        if(isGameMode1){
            resultLiveData.setValue(formatToTime(result));
            recordLiveData.setValue(formatToTime(record));
        }
        else {
            resultLiveData.setValue(Integer.toString(result));
            recordLiveData.setValue(Integer.toString(record));
        }
    }

    private String formatToTime(int number){
        int min = number / 60;
        int sec = number % 60;
        return String.format(Locale.ENGLISH, "%02d:%02d", min, sec);
    }
}
