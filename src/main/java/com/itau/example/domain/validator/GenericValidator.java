package com.itau.example.domain.validator;

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class GenericValidator<T> implements RequestValidator<T> {

    private final Predicate<T> predicate;
    private String name;

    @Override
    public void validate(T request) throws ValidationException {
        if (predicate.test(request)) {
            log.error("Validation failed for: [{}]", name);
            throw new ValidationException(name);
        }
    }
}
