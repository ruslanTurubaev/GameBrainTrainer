package com.example.braintrainer.data.storage.storageinterfaceimplementation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.braintrainer.data.storage.constants.Constants;
import com.example.braintrainer.data.storage.models.ResultDefaultValueStorageModel;
import com.example.braintrainer.data.storage.models.ResultStorageModel;
import com.example.braintrainer.data.storage.storageinterface.ResultStorageInterface;

public class ResultStorage implements ResultStorageInterface {
    private Context context;
    private SharedPreferences sharedPreferences;

    public ResultStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void setResult(ResultStorageModel resultStorageModel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.RESULT, resultStorageModel.getResult());
        editor.apply();
    }

    @Override
    public ResultStorageModel getResult(ResultDefaultValueStorageModel resultDefaultValueStorageModel) {
        int result = sharedPreferences.getInt(Constants.RESULT, resultDefaultValueStorageModel.getResultDefaultValue());
        return new ResultStorageModel(result);
    }
}
