package com.demo.demo.Features.Param.User.Validations;

import com.demo.demo.Entities.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserValidations {
    public static List<String> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String fieldName = error.getField();
                try {
                    Field field = UserModel.class.getDeclaredField(fieldName);
                    JsonProperty jsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
                    if (jsonPropertyAnnotation != null) {
                        fieldName = jsonPropertyAnnotation.value();
                    }
                } catch (NoSuchFieldException ignored) {
                }
                errors.add(fieldName + ": " + error.getDefaultMessage());
            }
            return errors;
        }
        return Collections.emptyList();
    }
}
