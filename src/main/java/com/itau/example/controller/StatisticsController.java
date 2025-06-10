package com.itau.example.controller;

import com.itau.example.domain.dto.StatisticsDto;
import com.itau.example.domain.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/estatistica", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatisticsFromLastMinute() {
        return ResponseEntity.ok(statisticsService.getStatisticsFromLast60Seconds());
    }
}
