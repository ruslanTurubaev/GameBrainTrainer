package com.example.braintrainer.domain.usecase;

import com.example.braintrainer.domain.models.GeneratedStepDomainModel;

public class GenerateNextStepUseCase {
    private int number1, number2, numberResult, numberFalse;

    public GeneratedStepDomainModel execute(){
        String arithmeticalExpression;
        boolean isTrueAnswer;

        generateNumbers();

        int choose = (int) (Math.random() * 2);
        if(choose == 1){
            arithmeticalExpression = number1 + " + " + number2 + " = " + numberResult;
            isTrueAnswer = true;
        }
        else {
            arithmeticalExpression = number1 + " + " + number2 + " = " + numberFalse;
            isTrueAnswer = false;
        }

        return new GeneratedStepDomainModel(arithmeticalExpression, isTrueAnswer);
    }

    /*
    the method generate 4 numbers, 2 numbers generated within given range randomly - terms.
    the third number is the result of the summation of the two above numbers - sum result.
    the fourth number is generated randomly within the range from the max between the two terms to the sum result.
    */
    private void generateNumbers(){
        int min = 3;
        int max = 20;
        int min_generated_number;

        number1 = (int) (Math.random() * (max - min) + min);
        number2 = (int) (Math.random() * (max - min) + min);

        min_generated_number = Math.max(number1, number2);

        numberResult = number1 + number2;
        numberFalse = (int) (Math.random() * (numberResult - min_generated_number) + min_generated_number);
    }
}
