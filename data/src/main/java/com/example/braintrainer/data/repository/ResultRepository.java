package com.example.braintrainer.data.repository;

import com.example.braintrainer.data.storage.models.ResultDefaultValueStorageModel;
import com.example.braintrainer.data.storage.models.ResultStorageModel;
import com.example.braintrainer.data.storage.storageinterface.ResultStorageInterface;
import com.example.braintrainer.data.storage.storageinterfaceimplementation.ResultStorage;
import com.example.braintrainer.domain.models.ResultDefaultValueDomainModel;
import com.example.braintrainer.domain.models.ResultDomainModel;
import com.example.braintrainer.domain.repository.ResultRepositoryInterface;

public class ResultRepository implements ResultRepositoryInterface {
    private ResultStorageInterface resultStorage;

    public ResultRepository(ResultStorageInterface resultStorage) {
        this.resultStorage = resultStorage;
    }

    @Override
    public void setResult(ResultDomainModel resultDomainModel) {
        ResultStorageModel resultStorageModel = resultMapToStorage(resultDomainModel);
        resultStorage.setResult(resultStorageModel);
    }

    @Override
    public ResultDomainModel getResult(ResultDefaultValueDomainModel resultDefaultValueDomainModel) {
        ResultDefaultValueStorageModel resultDefaultValueStorageModel = resultDefaultValueMapToStorage(resultDefaultValueDomainModel);
        ResultStorageModel resultStorageModel = resultStorage.getResult(resultDefaultValueStorageModel);
        return resultMapToDomain(resultStorageModel);
    }

    private ResultStorageModel resultMapToStorage(ResultDomainModel resultDomainModel){
        return new ResultStorageModel(resultDomainModel.getResult());
    }

    private ResultDomainModel resultMapToDomain(ResultStorageModel resultStorageModel){
        return new ResultDomainModel(resultStorageModel.getResult());
    }

    private ResultDefaultValueStorageModel resultDefaultValueMapToStorage(ResultDefaultValueDomainModel resultDefaultValueDomainModel){
        return new ResultDefaultValueStorageModel(resultDefaultValueDomainModel.getResultDefaultValue());
    }
}
