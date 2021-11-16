package com.rango.alere.controllers.dtos.validations;

import com.rango.alere.controllers.dtos.validations.constraints.TermAndConditionsIsTrueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TermAndConditionsIsTrueValidator.class)
public @interface TermAndConditionsIsTrue {

    String message() default "Voce deve aceitar os termos e condicoes para prosseguir";
    Class<?>[] groups() default { };
    Class<? extends Payload>[]  payload() default {};

}
