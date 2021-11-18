package com.rango.alere.controllers.dtos.validations;

import com.rango.alere.controllers.dtos.validations.constraints.ValidImageFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidImageFileValidator.class)
public @interface ValidImageFile {
    String message() default "Por favor escolha um arquivo valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
