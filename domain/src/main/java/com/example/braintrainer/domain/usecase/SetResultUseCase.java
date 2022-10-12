package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.ResultDomainModel;
import com.example.braintrainer.domain.repository.ResultRepositoryInterface;

public class SetResultUseCase {
    private ResultRepositoryInterface resultRepository;

    public SetResultUseCase(ResultRepositoryInterface resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void execute(ResultDomainModel resultDomainModel){
        resultRepository.setResult(resultDomainModel);
    }
}
