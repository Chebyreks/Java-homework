package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import edu.phystech.hw5.exception.ValidationException;

import java.lang.reflect.Field;

/**
 * @author kzlv4natoly
 */
public interface Validator {
    void validate(Object object);

    static Validator getValidator() {
        return object -> {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {

                    field.setAccessible(true);
                    String value;
                    try {
                        value = (String)field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    NotBlank notBlankAnnotation = field.getAnnotation(NotBlank.class);
                    if (notBlankAnnotation != null) {
                        if (value == null || value.trim().isEmpty()) {
                            String message = notBlankAnnotation.message();
                            message = message.isEmpty() ? message = "Field " + field.getName() + " must not be empty" : message;
                            throw new ValidationException(message);
                        }
                    }

                    Size sizeAnnotation = field.getAnnotation(Size.class);
                    if (sizeAnnotation != null) {
                        String message = sizeAnnotation.message();
                        if (value == null || value.trim().isEmpty()) {
                            message = message.isEmpty() ? "Field " + field.getName() + " must not be empty" : message;
                            throw new ValidationException(message);
                        } else {
                            int min = sizeAnnotation.min();
                            int max = sizeAnnotation.max();
                            int length = value.length();
                            if (length < min || length > max) {
                                message = message.isEmpty() ? "Field " + field.getName() + " length must be from " + min + " to " + max : message;
                                throw new ValidationException(message);
                            }
                        }

                    }
                }
            }
        };
    }
}