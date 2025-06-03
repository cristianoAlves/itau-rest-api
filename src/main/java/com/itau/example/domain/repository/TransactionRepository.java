package com.itau.example.domain.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository<T> {

    private final List<T> repository = new ArrayList<>();

    public void insertTransaction(T t) {
        repository.add(t);
    }

    public void cleanAllTransactions() {
        repository.clear();
    }

    public List<T> getAllTransactions() {
        return repository;
    }
}
