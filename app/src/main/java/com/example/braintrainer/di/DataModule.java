package com.example.braintrainer.di;

import android.content.Context;

import com.example.braintrainer.data.repository.RecordsRepository;
import com.example.braintrainer.data.repository.ResultRepository;
import com.example.braintrainer.data.repository.SettingsRepository;
import com.example.braintrainer.data.storage.storageinterface.RecordsStorageInterface;
import com.example.braintrainer.data.storage.storageinterface.ResultStorageInterface;
import com.example.braintrainer.data.storage.storageinterface.SettingsStorageInterface;
import com.example.braintrainer.data.storage.storageinterfaceimplementation.RecordsStorage;
import com.example.braintrainer.data.storage.storageinterfaceimplementation.ResultStorage;
import com.example.braintrainer.data.storage.storageinterfaceimplementation.SettingsStorage;
import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;
import com.example.braintrainer.domain.repository.ResultRepositoryInterface;
import com.example.braintrainer.domain.repository.SettingsRepositoryInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    public RecordsRepositoryInterface getRecordsRepository(RecordsStorageInterface recordsStorageInterface){
        return new RecordsRepository(recordsStorageInterface);
    }

    @Provides
    @Singleton
    public ResultRepositoryInterface getResultRepository(ResultStorageInterface resultStorageInterface){
        return new ResultRepository(resultStorageInterface);
    }

    @Provides
    @Singleton
    public SettingsRepositoryInterface getSettingsRepository(SettingsStorageInterface settingsStorageInterface){
        return new SettingsRepository(settingsStorageInterface);
    }

    @Provides
    @Singleton
    public RecordsStorageInterface getRecordsStorageInterface(@ApplicationContext Context context){
        return new RecordsStorage(context);
    }

    @Provides
    @Singleton
    public ResultStorageInterface getResultStorageInterface(@ApplicationContext Context context){
        return new ResultStorage(context);
    }

    @Provides
    @Singleton
    public SettingsStorageInterface getSettingsStorageInterface(@ApplicationContext Context context){
        return new SettingsStorage(context);
    }
}
