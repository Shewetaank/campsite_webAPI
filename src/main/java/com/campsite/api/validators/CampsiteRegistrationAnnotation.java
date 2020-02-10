package com.campsite.api.validators;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { CampsiteRegistrationValidator.class })
@Documented
public @interface CampsiteRegistrationAnnotation {

    String message() default "Please check input value. All fields are required before booking campsite.";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};

}
