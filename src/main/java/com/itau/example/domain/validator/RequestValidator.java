package com.itau.example.domain.validator;

@FunctionalInterface
public interface RequestValidator<T> {

    void validate(T request) throws ValidationException;
}
