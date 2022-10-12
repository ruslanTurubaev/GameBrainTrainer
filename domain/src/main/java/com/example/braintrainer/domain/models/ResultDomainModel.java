package com.example.braintrainer.domain.models;

import java.util.Objects;

public class ResultDomainModel {

    private int result;

    public ResultDomainModel(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDomainModel that = (ResultDomainModel) o;
        return getResult() == that.getResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult());
    }
}
