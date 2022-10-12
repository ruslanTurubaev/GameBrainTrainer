package com.example.braintrainer.domain.models;

import java.util.Objects;

public class GeneratedStepDomainModel {

    private String arithmeticalExpression;
    private boolean isCorrectExpression;

    public GeneratedStepDomainModel(String arithmeticalExpression, boolean isCorrectExpression) {
        this.arithmeticalExpression = arithmeticalExpression;
        this.isCorrectExpression = isCorrectExpression;
    }

    public String getArithmeticalExpression() {
        return arithmeticalExpression;
    }

    public void setArithmeticalExpression(String arithmeticalExpression) {
        this.arithmeticalExpression = arithmeticalExpression;
    }

    public boolean isCorrectExpression() {
        return isCorrectExpression;
    }

    public void setCorrectExpression(boolean correctExpression) {
        isCorrectExpression = correctExpression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneratedStepDomainModel that = (GeneratedStepDomainModel) o;
        return isCorrectExpression() == that.isCorrectExpression() && Objects.equals(getArithmeticalExpression(), that.getArithmeticalExpression());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArithmeticalExpression(), isCorrectExpression());
    }
}
