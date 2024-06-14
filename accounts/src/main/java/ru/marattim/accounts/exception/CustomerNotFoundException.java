package ru.marattim.accounts.exception;

public class CustomerNotFoundException extends BusinessException {
    public CustomerNotFoundException(long id) {
        super("Пользователь с id = %s не найден".formatted(id));
    }
}
