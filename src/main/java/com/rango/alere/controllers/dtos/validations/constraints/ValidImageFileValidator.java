package com.rango.alere.controllers.dtos.validations.constraints;

import com.rango.alere.controllers.dtos.validations.ValidImageFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidImageFileValidator implements ConstraintValidator<ValidImageFile, MultipartFile> {
    @Override
    public void initialize(ValidImageFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());

            return  !filename.isBlank() && !filename.contains("..");
        } catch (Exception e) {
            return false;
        }
    }

}
