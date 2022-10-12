package com.example.braintrainer.domain.models;

import java.util.Objects;

public class RecordDomainModel {
    private String flag;
    private int record;

    public RecordDomainModel(String flag, int record) {
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
        RecordDomainModel that = (RecordDomainModel) o;
        return getRecord() == that.getRecord() && Objects.equals(getFlag(), that.getFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlag(), getRecord());
    }
}
