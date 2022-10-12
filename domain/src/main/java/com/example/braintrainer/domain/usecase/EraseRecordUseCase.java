package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.RecordsTypeForEraseDomainModel;
import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;

public class EraseRecordUseCase {
    private RecordsRepositoryInterface recordsRepository;

    public EraseRecordUseCase(RecordsRepositoryInterface recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public void execute(RecordsTypeForEraseDomainModel recordsTypeForEraseDomainModel){
        recordsRepository.eraseRecord(recordsTypeForEraseDomainModel);
    }
}
