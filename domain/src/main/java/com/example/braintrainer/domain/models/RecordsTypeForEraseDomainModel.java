package com.example.braintrainer.domain.models;

import java.util.Objects;

public class RecordsTypeForEraseDomainModel {
    private String flag;

    public RecordsTypeForEraseDomainModel(String flag) {
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
        RecordsTypeForEraseDomainModel that = (RecordsTypeForEraseDomainModel) o;
        return Objects.equals(getFlag(), that.getFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlag());
    }
}
