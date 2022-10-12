package com.example.braintrainer.data.storage.models;

import java.util.Objects;

public class ResultDefaultValueStorageModel {
    private int resultDefaultValue;

    public ResultDefaultValueStorageModel(int resultDefaultValue) {
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
        ResultDefaultValueStorageModel that = (ResultDefaultValueStorageModel) o;
        return getResultDefaultValue() == that.getResultDefaultValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResultDefaultValue());
    }
}
