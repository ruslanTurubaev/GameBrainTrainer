package com.example.braintrainer.data.storage.models;

import java.util.Objects;

public class RecordsTypeForEraseStorageModel {
    private String flag;

    public RecordsTypeForEraseStorageModel(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordsTypeForEraseStorageModel that = (RecordsTypeForEraseStorageModel) o;
        return Objects.equals(getFlag(), that.getFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlag());
    }
}
