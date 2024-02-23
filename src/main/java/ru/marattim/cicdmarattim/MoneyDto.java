package ru.marattim.cicdmarattim;

import org.openapitools.model.Currency;

import java.math.BigDecimal;

public record MoneyDto(Currency currency, BigDecimal amount) {
}
