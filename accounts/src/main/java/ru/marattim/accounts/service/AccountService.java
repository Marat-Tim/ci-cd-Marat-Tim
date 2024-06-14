package ru.marattim.accounts.service;

import ru.marattim.accounts.dto.AddAccountRequest;
import ru.marattim.accounts.dto.GetAccountResponse;

import java.math.BigDecimal;

public interface AccountService {
    long addAccount(AddAccountRequest request);

    GetAccountResponse getAccount(long accountNumber);

    void topUp(long accountNumber, BigDecimal amount);
}
