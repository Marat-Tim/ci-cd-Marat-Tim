package ru.marattim.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.springframework.stereotype.Service;
import ru.marattim.accounts.dto.AddCustomerRequest;
import ru.marattim.accounts.dto.AddCustomerResponse;
import ru.marattim.accounts.entity.CustomerEntity;
import ru.marattim.accounts.exception.CustomerNotFoundException;
import ru.marattim.accounts.repository.CustomerRepository;
import ru.marattim.accounts.service.ConverterClient;
import ru.marattim.accounts.service.CustomerService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final ConverterClient converterClient;

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest request) {
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setBirthDay(request.getBirthDay());
        return new AddCustomerResponse(customerRepository.save(entity).getId());
    }

    @Override
    public BigDecimal getSumInCurrency(long customerId, Currency currency) {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId))
                .getAccounts()
                .stream()
                .parallel()
                .map(account -> converterClient.convert(account.getCurrency(), currency, account.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
