package com.itau.example.controller;

import com.itau.example.domain.request.TransactionBody;
import com.itau.example.domain.service.TransactionService;
import com.itau.example.domain.service.ValidationService;
import com.itau.example.domain.validator.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/transacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final ValidationService validationService;
    private final TransactionService transactionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionBody transactionBody) {

        try {
            validationService.validate(transactionBody);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        transactionService.addTransaction(transactionBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAllTransactions() {
        transactionService.removeAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
