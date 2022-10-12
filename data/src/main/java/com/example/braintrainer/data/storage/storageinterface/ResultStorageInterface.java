package com.example.braintrainer.data.storage.storageinterface;

import com.example.braintrainer.data.storage.models.ResultDefaultValueStorageModel;
import com.example.braintrainer.data.storage.models.ResultStorageModel;

public interface ResultStorageInterface {

    void setResult(ResultStorageModel resultStorageModel);

    ResultStorageModel getResult(ResultDefaultValueStorageModel resultDefaultValueStorageModel);
}
