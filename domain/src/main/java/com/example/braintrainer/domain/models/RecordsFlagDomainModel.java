package com.example.braintrainer.domain.models;

import java.util.Objects;

public class RecordsFlagDomainModel {
    private String flag;

    public RecordsFlagDomainModel(String flag) {
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
        RecordsFlagDomainModel that = (RecordsFlagDomainModel) o;
        return Objects.equals(getFlag(), that.getFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlag());
    }
}
