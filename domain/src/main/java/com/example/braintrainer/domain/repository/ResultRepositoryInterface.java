package com.example.braintrainer.domain.repository;

import com.example.braintrainer.domain.models.ResultDefaultValueDomainModel;
import com.example.braintrainer.domain.models.ResultDomainModel;

public interface ResultRepositoryInterface {
    void setResult (ResultDomainModel resultDomainModel);

    ResultDomainModel getResult(ResultDefaultValueDomainModel resultDefaultValueDomainModel);
}
