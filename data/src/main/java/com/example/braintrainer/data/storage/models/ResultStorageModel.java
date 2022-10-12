package com.example.braintrainer.data.storage.models;

import java.util.Objects;

public class ResultStorageModel {
    private int result;

    public ResultStorageModel(int result) {
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
        ResultStorageModel that = (ResultStorageModel) o;
        return getResult() == that.getResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult());
    }
}
