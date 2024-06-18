package ru.marattim.accounts.exception;

public class NotEnoughMoneyException extends BusinessException {
    public NotEnoughMoneyException() {
        super("Недостаточно средств");
    }
}
