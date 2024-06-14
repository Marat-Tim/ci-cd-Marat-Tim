package ru.marattim.accounts.service;

import org.openapitools.model.Currency;

import java.math.BigDecimal;

public interface ConverterClient {
    BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
