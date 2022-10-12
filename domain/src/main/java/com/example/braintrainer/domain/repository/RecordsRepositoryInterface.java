package com.example.braintrainer.domain.repository;

import com.example.braintrainer.domain.models.RecordDomainModel;
import com.example.braintrainer.domain.models.RecordsFlagDomainModel;
import com.example.braintrainer.domain.models.RecordsTypeForEraseDomainModel;

public interface RecordsRepositoryInterface {

    void setRecord(RecordDomainModel recordDomainModel);

    RecordDomainModel getRecord(RecordsFlagDomainModel recordsFlagDomainModel);

    void eraseAllRecords();

    void eraseRecord(RecordsTypeForEraseDomainModel recordsTypeForEraseDomainModel);
}
