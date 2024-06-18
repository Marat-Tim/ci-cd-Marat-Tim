package ru.marattim.accounts.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.springframework.stereotype.Service;
import ru.marattim.accounts.dto.AddAccountRequest;
import ru.marattim.accounts.dto.GetAccountResponse;
import ru.marattim.accounts.entity.AccountEntity;
import ru.marattim.accounts.entity.CustomerEntity;
import ru.marattim.accounts.exception.AccountExistsException;
import ru.marattim.accounts.exception.AccountNotFoundException;
import ru.marattim.accounts.exception.CustomerNotFoundException;
import ru.marattim.accounts.repository.AccountRepository;
import ru.marattim.accounts.repository.CustomerRepository;
import ru.marattim.accounts.service.AccountService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    @Override
    public long addAccount(AddAccountRequest request) {
        if (!customerRepository.existsById(request.getCustomerId())) {
            throw new CustomerNotFoundException(request.getCustomerId());
        }
        if (accountRepository.existsByCustomerIdAndCurrency(request.getCustomerId(), request.getCurrency())) {
            throw new AccountExistsException(request.getCurrency());
        }
        return addAccount(request.getCurrency(), request.getCustomerId());
    }

    @Override
    public GetAccountResponse getAccount(long accountNumber) {
        return accountRepository.findById(accountNumber)
                .map(account -> new GetAccountResponse(account.getAmount(), account.getCurrency()))
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    @Override
    @Transactional
    public void topUp(long accountNumber, BigDecimal amount) {
        accountRepository.findByIdWithLock(accountNumber)
                .ifPresentOrElse(account -> {
                    account.setAmount(account.getAmount().add(amount));
                    accountRepository.save(account);
                }, () -> {
                    throw new AccountNotFoundException(accountNumber);
                });
    }

    private long addAccount(Currency currency, long customerId) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAmount(BigDecimal.ZERO);
        accountEntity.setCurrency(currency);
        CustomerEntity customer = customerRepository.getReferenceById(customerId);
        accountEntity.setCustomer(customer);
        return accountRepository.save(accountEntity).getId();
    }
}
