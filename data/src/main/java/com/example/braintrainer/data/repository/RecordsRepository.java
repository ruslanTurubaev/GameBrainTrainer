package com.example.braintrainer.data.repository;

import com.example.braintrainer.data.storage.models.RecordStorageModel;
import com.example.braintrainer.data.storage.models.RecordsFlagStorageModel;
import com.example.braintrainer.data.storage.models.RecordsTypeForEraseStorageModel;
import com.example.braintrainer.data.storage.storageinterface.RecordsStorageInterface;
import com.example.braintrainer.domain.models.RecordDomainModel;
import com.example.braintrainer.domain.models.RecordsFlagDomainModel;
import com.example.braintrainer.domain.models.RecordsTypeForEraseDomainModel;
import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;

public class RecordsRepository implements RecordsRepositoryInterface {
    private RecordsStorageInterface recordsStorage;

    public RecordsRepository(RecordsStorageInterface recordsStorage) {
        this.recordsStorage = recordsStorage;
    }

    @Override
    public void setRecord(RecordDomainModel recordDomainModel) {
        RecordStorageModel recordStorageModel = recordsModelMapToStorage(recordDomainModel);
        recordsStorage.setRecord(recordStorageModel);
    }

    @Override
    public RecordDomainModel getRecord(RecordsFlagDomainModel recordsFlagDomainModel) {
        RecordsFlagStorageModel recordsFlagStorageModel = recordsFlagMapToStorage(recordsFlagDomainModel);
        RecordStorageModel recordStorageModel = recordsStorage.getRecord(recordsFlagStorageModel);
        return recordsModelMapToDomain(recordStorageModel);
    }

    @Override
    public void eraseAllRecords() {
        recordsStorage.eraseAllRecords();
    }

    @Override
    public void eraseRecord(RecordsTypeForEraseDomainModel recordsTypeForEraseDomainModel) {
        RecordsTypeForEraseStorageModel recordsTypeForEraseStorageModel = recordsTypeForEraseMapToStorage(recordsTypeForEraseDomainModel);
        recordsStorage.EraseRecord(recordsTypeForEraseStorageModel);
    }

    private RecordStorageModel recordsModelMapToStorage(RecordDomainModel recordDomainModel){
        return new RecordStorageModel(recordDomainModel.getFlag(), recordDomainModel.getRecord());
    }

    private RecordDomainModel recordsModelMapToDomain(RecordStorageModel recordStorageModel){
        return new RecordDomainModel(recordStorageModel.getFlag(), recordStorageModel.getRecord());
    }

    private RecordsFlagStorageModel recordsFlagMapToStorage(RecordsFlagDomainModel recordsFlagDomainModel){
        return new RecordsFlagStorageModel(recordsFlagDomainModel.getFlag());
    }

    private RecordsTypeForEraseStorageModel recordsTypeForEraseMapToStorage(RecordsTypeForEraseDomainModel recordsTypeForEraseDomainModel){
        return new RecordsTypeForEraseStorageModel(recordsTypeForEraseDomainModel.getFlag());
    }
}
