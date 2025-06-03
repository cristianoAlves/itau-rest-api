package com.itau.example.domain.validator;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ValidatorFactory<T> {

    private final Set<RequestValidator<T>> registeredValidators = new HashSet<>();

    public void registerValidator(RequestValidator<T> validator) {
        registeredValidators.add(validator);
    }

    public RequestValidator<T> createValidator(Predicate<T> predicate, String name) {
        return new GenericValidator<>(predicate, name);
    }
}
