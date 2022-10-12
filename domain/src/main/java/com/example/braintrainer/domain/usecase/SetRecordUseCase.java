package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.RecordDomainModel;
import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;

public class SetRecordUseCase {
    private RecordsRepositoryInterface recordsRepository;

    public SetRecordUseCase(RecordsRepositoryInterface recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public void execute(RecordDomainModel recordDomainModel){
        recordsRepository.setRecord(recordDomainModel);
    }
}
