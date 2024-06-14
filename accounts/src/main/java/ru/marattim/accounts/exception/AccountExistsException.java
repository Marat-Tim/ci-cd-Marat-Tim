package ru.marattim.accounts.exception;

import org.openapitools.model.Currency;

public class AccountExistsException extends BusinessException {
    public AccountExistsException(Currency currency) {
        super("Аккаунт в счете %s уже существует у данного пользователя".formatted(currency));
    }
}
