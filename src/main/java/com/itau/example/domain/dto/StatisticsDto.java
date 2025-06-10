package com.itau.example.domain.dto;

import java.util.DoubleSummaryStatistics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StatisticsDto {

    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

    public StatisticsDto(DoubleSummaryStatistics doubleSummaryStatistics) {
        this.count = doubleSummaryStatistics.getCount();
        this.sum = doubleSummaryStatistics.getSum();
        this.avg = doubleSummaryStatistics.getAverage();
        this.min = doubleSummaryStatistics.getMin();
        this.max = doubleSummaryStatistics.getMax();
    }
}
