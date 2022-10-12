package com.example.braintrainer.data.storage.storageinterfaceimplementation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.data.storage.models.RecordStorageModel;
import com.example.braintrainer.data.storage.models.RecordsFlagStorageModel;
import com.example.braintrainer.data.storage.models.RecordsTypeForEraseStorageModel;
import com.example.braintrainer.data.storage.storageinterface.RecordsStorageInterface;

public class RecordsStorage implements RecordsStorageInterface {
    private Context context;
    private SharedPreferences sharedPreferences;

    public RecordsStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void eraseAllRecords() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.ANSWERS_10_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.ANSWERS_25_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.ANSWERS_50_RECORD, Integer.MAX_VALUE);
        editor.putInt(Constants.TIME_10_RECORD, 0);
        editor.putInt(Constants.TIME_30_RECORD, 0);
        editor.putInt(Constants.TIME_60_RECORD, 0);
        editor.apply();
    }

    @Override
    public void EraseRecord(RecordsTypeForEraseStorageModel recordsTypeForEraseStorageModel) {
        String flag = recordsTypeForEraseStorageModel.getFlag();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(flag.equals(Constants.ANSWERS_10_RECORD) || flag.equals(Constants.ANSWERS_25_RECORD) || flag.equals(Constants.ANSWERS_50_RECORD)){
            editor.putInt(flag, Integer.MAX_VALUE);
        }
        else {
            editor.putInt(flag, 0);
        }

        editor.apply();
    }

    @Override
    public void setRecord(RecordStorageModel recordStorageModel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(recordStorageModel.getFlag(), recordStorageModel.getRecord());
        editor.apply();
    }

    @Override
    public RecordStorageModel getRecord(RecordsFlagStorageModel recordsFlagStorageModel) {
        String flag = recordsFlagStorageModel.getFlag();
        int record;

        if(flag.equals(Constants.ANSWERS_10_RECORD) || flag.equals(Constants.ANSWERS_25_RECORD) || flag.equals(Constants.ANSWERS_50_RECORD)){
            record = sharedPreferences.getInt(recordsFlagStorageModel.getFlag(), Integer.MAX_VALUE);
        }
        else {
            record = sharedPreferences.getInt(recordsFlagStorageModel.getFlag(), 0);
        }

        return new RecordStorageModel(flag, record);
    }
}
