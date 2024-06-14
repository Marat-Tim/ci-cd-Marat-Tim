package ru.marattim.converter.dto;

import org.openapitools.model.Currency;

import java.math.BigDecimal;

public record MoneyDto(Currency currency, BigDecimal amount) {
}
