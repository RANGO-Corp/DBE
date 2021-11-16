package com.rango.alere.controllers.dtos.validations.constraints;

import com.rango.alere.controllers.dtos.validations.TermAndConditionsIsTrue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class TermAndConditionsIsTrueValidator implements ConstraintValidator<TermAndConditionsIsTrue, Boolean> {
    @Override
    public void initialize(TermAndConditionsIsTrue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return (Objects.nonNull(value) && value);
    }

}
