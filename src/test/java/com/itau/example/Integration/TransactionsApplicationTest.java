package com.itau.example.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.itau.example.domain.request.TransactionBody;
import com.itau.example.domain.service.TransactionService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionsApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    void shouldAddTransactionOk() {
        TransactionBody payload = createTransaction(100, OffsetDateTime.now());

        var response = testRestTemplate.postForEntity("/transacao", payload, Map.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldGetErrorWhenAddingTransactionWithFutureDates() {
        TransactionBody payload = createTransaction(100, OffsetDateTime.now().plusMinutes(1));

        var response = testRestTemplate.postForEntity("/transacao", payload, Map.class);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void shouldGetErrorWhenAddingTransactionWithNegativeValue() {
        TransactionBody payload = createTransaction(-100, OffsetDateTime.now());

        var response = testRestTemplate.postForEntity("/transacao", payload, Map.class);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void shouldGetErrorWhenAddingTransactionWithNullPayload() {
        var response = testRestTemplate.postForEntity("/transacao", null, Map.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    void shouldGetErrorWhenAddingTransactionWithWrongPayload() {
        String payload = "Test";

        var response = testRestTemplate.postForEntity("/transacao", payload, Map.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    void shouldDeleteAllTransactions() {
        TransactionBody payload = createTransaction(100, OffsetDateTime.now());

        testRestTemplate.postForEntity("/transacao", payload, Map.class);
        testRestTemplate.postForEntity("/transacao", payload, Map.class);

        var response = testRestTemplate.exchange(
            "/transacao",
            HttpMethod.DELETE,
            null,
            Void.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService).removeAll();
    }

    private static TransactionBody createTransaction(int val, OffsetDateTime now) {
        return TransactionBody.builder()
            .valor(new BigDecimal(val))
            .dataHora(now)
            .build();
    }
}
