package ru.marattim.accounts.exception;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException(long accountNumber) {
        super("Счет с номером %s не найден".formatted(accountNumber));
    }
}
