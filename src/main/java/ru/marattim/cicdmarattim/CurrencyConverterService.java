package ru.marattim.cicdmarattim;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.openapitools.model.RatesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CurrencyConverterService {
    private final WebClient webClient;

    MoneyDto convert(Currency from, Currency to, BigDecimal amount) {
        RatesResponse response = webClient.get().retrieve().bodyToMono(RatesResponse.class).block();
        //noinspection DataFlowIssue
        var rates = response.getRates();
        return new MoneyDto(to, amount.multiply(rates.get(to.getValue())).divide(rates.get(from.getValue()),
                2, RoundingMode.HALF_EVEN));
    }
}
