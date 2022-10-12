package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.ResultDefaultValueDomainModel;
import com.example.braintrainer.domain.models.ResultDomainModel;
import com.example.braintrainer.domain.repository.ResultRepositoryInterface;

public class GetResultUseCase {
    private ResultRepositoryInterface resultRepository;

    public GetResultUseCase(ResultRepositoryInterface resultRepository) {
        this.resultRepository = resultRepository;
    }

    public ResultDomainModel execute(ResultDefaultValueDomainModel resultDefaultValueDomainModel){
        return resultRepository.getResult(resultDefaultValueDomainModel);
    }
}
