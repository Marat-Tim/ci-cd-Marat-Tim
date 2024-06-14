package ru.marattim.accounts.dto;

import org.openapitools.model.Currency;

import java.math.BigDecimal;

public record GetSumByCurrencyResponse(BigDecimal balance, Currency currency) {
}
