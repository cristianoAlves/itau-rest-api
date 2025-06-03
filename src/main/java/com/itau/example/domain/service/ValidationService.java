package com.itau.example.domain.service;

import com.itau.example.domain.request.TransactionBody;
import com.itau.example.domain.validator.RequestValidator;
import com.itau.example.domain.validator.ValidationException;
import com.itau.example.domain.validator.ValidatorFactory;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ValidationService {

    private final ValidatorFactory<TransactionBody> validatorFactory;

    public void validate(final TransactionBody transactionBody) throws ValidationException {
        List<RequestValidator<TransactionBody>> validators = List.of(
            validatorFactory.createValidator(r -> r.getDataHora().isAfter(OffsetDateTime.now()), "Data-hora should be before actual time"),
            validatorFactory.createValidator(r -> r.getValor().compareTo(BigDecimal.ZERO) < 0, "valor should be >= zero"),
            validatorFactory.createValidator(Objects::isNull, "Body cannot be null")
        );

        for (RequestValidator<TransactionBody> requestValidator : validators) {
            requestValidator.validate(transactionBody);
        }
    }
}
