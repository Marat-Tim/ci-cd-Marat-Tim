package ru.marattim.cicdmarattim;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyConverterController {
    private final CurrencyConverterService currencyConverterService;

    @GetMapping("convert")
    ResponseEntity<Object> convert(@RequestParam("from") String from,
                           @RequestParam("to") String to,
                           @RequestParam("amount") BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(new ErrorDto("Отрицательная сумма"));
        }
        Currency fromCurrency;
        Currency toCurrency;
        try {
            fromCurrency = Currency.fromValue(from);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorDto("Валюта %s недоступна".formatted(from)));
        }
        try {
            toCurrency = Currency.fromValue(to);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorDto("Валюта %s недоступна".formatted(to)));
        }
        MoneyDto moneyDto = currencyConverterService.convert(fromCurrency, toCurrency, amount);
        return ResponseEntity.ok(moneyDto);
    }
}
