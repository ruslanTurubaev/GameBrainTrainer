package com.example.braintrainer.data.storage.storageinterface;

import com.example.braintrainer.data.storage.models.RecordStorageModel;
import com.example.braintrainer.data.storage.models.RecordsFlagStorageModel;
import com.example.braintrainer.data.storage.models.RecordsTypeForEraseStorageModel;

public interface RecordsStorageInterface {
    void eraseAllRecords();

    void EraseRecord(RecordsTypeForEraseStorageModel recordsTypeForEraseStorageModel);

    void setRecord(RecordStorageModel recordStorageModel);

    RecordStorageModel getRecord(RecordsFlagStorageModel recordsFlagStorageModel);
}
