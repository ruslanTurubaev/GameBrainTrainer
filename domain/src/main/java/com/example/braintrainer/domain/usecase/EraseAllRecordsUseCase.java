package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.repository.RecordsRepositoryInterface;

public class EraseAllRecordsUseCase {
    private RecordsRepositoryInterface recordsRepository;

    public EraseAllRecordsUseCase(RecordsRepositoryInterface recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public void execute(){
        recordsRepository.eraseAllRecords();
    }
}
