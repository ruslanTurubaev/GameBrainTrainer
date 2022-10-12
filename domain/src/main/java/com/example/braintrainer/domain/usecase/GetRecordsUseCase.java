package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.RecordDomainModel;
import com.example.braintrainer.domain.models.RecordsFlagDomainModel;
import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;

public class GetRecordsUseCase {
    private RecordsRepositoryInterface recordsRepository;

    public GetRecordsUseCase(RecordsRepositoryInterface recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public RecordDomainModel execute(RecordsFlagDomainModel recordsFlagDomainModel){
        return recordsRepository.getRecord(recordsFlagDomainModel);
    }
}
