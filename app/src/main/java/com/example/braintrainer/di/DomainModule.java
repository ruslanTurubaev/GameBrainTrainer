package com.example.braintrainer.di;

import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;
import com.example.braintrainer.domain.repository.ResultRepositoryInterface;
import com.example.braintrainer.domain.repository.SettingsRepositoryInterface;
import com.example.braintrainer.domain.usecase.EraseAllRecordsUseCase;
import com.example.braintrainer.domain.usecase.EraseRecordUseCase;
import com.example.braintrainer.domain.usecase.GenerateNextStepUseCase;
import com.example.braintrainer.domain.usecase.GetRecordsUseCase;
import com.example.braintrainer.domain.usecase.GetResultUseCase;
import com.example.braintrainer.domain.usecase.GetSettingsUseCase;
import com.example.braintrainer.domain.usecase.SetRecordUseCase;
import com.example.braintrainer.domain.usecase.SetResultUseCase;
import com.example.braintrainer.domain.usecase.SetSettingsUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class DomainModule {

    @Provides
    public EraseAllRecordsUseCase getEraseAllRecordsUseCase(RecordsRepositoryInterface recordsRepositoryInterface){
        return new EraseAllRecordsUseCase(recordsRepositoryInterface);
    }

    @Provides
    public EraseRecordUseCase getEraseRecordUseCase(RecordsRepositoryInterface recordsRepositoryInterface){
        return new EraseRecordUseCase(recordsRepositoryInterface);
    }

    @Provides
    public GenerateNextStepUseCase getGenerateNextStepUseCase(){
        return new GenerateNextStepUseCase();
    }

    @Provides
    public GetRecordsUseCase getGetRecordsUseCase(RecordsRepositoryInterface recordsRepositoryInterface){
        return new GetRecordsUseCase(recordsRepositoryInterface);
    }

    @Provides
    public GetResultUseCase getGetResultUseCase(ResultRepositoryInterface resultRepositoryInterface){
        return new GetResultUseCase(resultRepositoryInterface);
    }

    @Provides
    public GetSettingsUseCase getGetSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface){
        return new GetSettingsUseCase(settingsRepositoryInterface);
    }

    @Provides
    public SetRecordUseCase getSetRecordUseCase(RecordsRepositoryInterface recordsRepositoryInterface){
        return new SetRecordUseCase(recordsRepositoryInterface);
    }

    @Provides
    public SetResultUseCase getSetResultUseCase(ResultRepositoryInterface resultRepositoryInterface){
        return new SetResultUseCase(resultRepositoryInterface);
    }

    @Provides
    public SetSettingsUseCase getSetSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface){
        return new SetSettingsUseCase(settingsRepositoryInterface);
    }
}
