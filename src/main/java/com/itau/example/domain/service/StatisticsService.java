package com.itau.example.domain.service;

import com.itau.example.domain.dto.StatisticsDto;
import com.itau.example.domain.entity.TransactionEntity;
import com.itau.example.domain.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatisticsService {

    @Value("${application.interval.minutes:1}")
    private Integer interval;

    @Autowired
    private TransactionRepository<TransactionEntity> transactionRepository;

    public StatisticsDto getStatisticsFromLast60Seconds() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime timeAgo = now.minusMinutes(interval);
        log.info("Getting transactions from last [{}] minutes, starting on : [{}]", timeAgo, now);

        BigDecimal[] array = transactionRepository.getAllTransactions().stream()
            .filter(t -> t.getDataHora().isBefore(timeAgo) && !t.getDataHora().isAfter(now))
            .map(TransactionEntity::getValor).toArray(BigDecimal[]::new);

        DoubleStream doubleStream = Arrays.stream(array).mapToDouble(BigDecimal::doubleValue);
        return new StatisticsDto(doubleStream.summaryStatistics());
    }
}
