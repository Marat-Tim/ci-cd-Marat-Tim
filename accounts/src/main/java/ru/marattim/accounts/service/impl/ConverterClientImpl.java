package ru.marattim.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.marattim.accounts.service.ConverterClient;
import ru.marattim.common.dto.MoneyDto;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConverterClientImpl implements ConverterClient {
    private final WebClient webClient;

    @Override
    @SuppressWarnings("java:S2259")
    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        if (from == to) {
            return amount;
        }
        //noinspection DataFlowIssue
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/convert")
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .queryParam("amount", amount)
                        .build()
                )
                .retrieve()
                .bodyToMono(MoneyDto.class)
                .block()
                .amount();
    }
}
