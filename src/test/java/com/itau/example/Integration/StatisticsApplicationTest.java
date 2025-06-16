package com.itau.example.Integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.itau.example.domain.dto.StatisticsDto;
import com.itau.example.domain.request.TransactionBody;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticsApplicationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void validateLastMinuteStatistic(){
        TransactionBody payload = TransactionBody.builder()
            .valor(new BigDecimal(100))
            .dataHora(OffsetDateTime.now().minusMinutes(1))
            .build();

        testRestTemplate.postForEntity("/transacao", payload, Map.class);
        var response = testRestTemplate.getForEntity("/estatistica", StatisticsDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAvg()).isEqualTo(100L);
        assertThat(response.getBody().getSum()).isEqualTo(100L);
        assertThat(response.getBody().getMin()).isEqualTo(100L);
        assertThat(response.getBody().getMax()).isEqualTo(100L);
        assertThat(response.getBody().getCount()).isEqualTo(1L);
    }
}
