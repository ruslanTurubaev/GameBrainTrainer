package com.example.braintrainer.data.storage.models;

import java.util.Objects;

public class RecordStorageModel {
    private String flag;
    private int record;

    public RecordStorageModel(String flag, int record) {
        this.flag = flag;
        this.record = record;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordStorageModel that = (RecordStorageModel) o;
        return getRecord() == that.getRecord() && Objects.equals(getFlag(), that.getFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlag(), getRecord());
    }
}
