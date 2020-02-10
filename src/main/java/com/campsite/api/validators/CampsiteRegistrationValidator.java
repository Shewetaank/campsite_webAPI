package com.campsite.api.validators;

import com.campsite.api.entity.CampsiteRegistration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class CampsiteRegistrationValidator implements ConstraintValidator<CampsiteRegistrationAnnotation, CampsiteRegistration> {

    @Override
    public void initialize(CampsiteRegistrationAnnotation campsiteRegistrationAnnotation) {

    }

    @Override
    public boolean isValid(CampsiteRegistration campsiteRegistration, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate("Please check input value. All fields are required before booking campsite.").addConstraintViolation();
        return firstNamePresent(campsiteRegistration.getFirstName()) && lastNamePresent(campsiteRegistration.getLastName()) && validateEmail(campsiteRegistration.getEmail());
    }

    private boolean firstNamePresent(String firstName) {
        return firstName.trim().length() > 0;
    }

    private boolean lastNamePresent(String lastName) {
        return lastName.trim().length() > 0;
    }

    private boolean validateEmail(String email){
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }
}
