package ru.marattim.accounts.service;

import java.math.BigDecimal;

public interface TransferService {
    void transfer(long receiverAccount, long senderAccount, BigDecimal amountInSenderCurrency);
}
