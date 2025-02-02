package dev.projekt_inzynierski.controller.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ConstraintViolationException ex) {
        Map<String, Object> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        }

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        String message = ex.getMessage().replace("{", "").replace("}", "");
        String[] errorPairs = message.split(", ");

        for (String pair : errorPairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                errors.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }



}

