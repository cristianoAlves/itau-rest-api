package com.itau.example.domain.service;

import com.itau.example.domain.entity.TransactionEntity;
import com.itau.example.domain.mapper.TransactionMapper;
import com.itau.example.domain.repository.TransactionRepository;
import com.itau.example.domain.request.TransactionBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository<TransactionEntity> transactionRepository;
    private final TransactionMapper mapper;

    public void addTransaction(final TransactionBody transactionBody) {
        log.info("Adding new transaction [{}]", transactionBody);
        transactionRepository.insertTransaction(mapper.from(transactionBody));
    }

    public void removeAll() {
        log.info("Removing all transactions");
        transactionRepository.cleanAllTransactions();
    }

}
