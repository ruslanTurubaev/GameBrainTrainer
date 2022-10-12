package com.example.braintrainer.domain.models;

import java.util.Objects;

public class ResultDefaultValueDomainModel {
    private int resultDefaultValue;

    public ResultDefaultValueDomainModel(int resultDefaultValue) {
        this.resultDefaultValue = resultDefaultValue;
    }

    public int getResultDefaultValue() {
        return resultDefaultValue;
    }

    public void setResultDefaultValue(int resultDefaultValue) {
        this.resultDefaultValue = resultDefaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDefaultValueDomainModel that = (ResultDefaultValueDomainModel) o;
        return getResultDefaultValue() == that.getResultDefaultValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResultDefaultValue());
    }
}
