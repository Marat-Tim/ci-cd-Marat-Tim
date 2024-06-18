package ru.marattim.accounts.service;

import org.openapitools.model.Currency;
import ru.marattim.accounts.dto.AddCustomerRequest;
import ru.marattim.accounts.dto.AddCustomerResponse;

import java.math.BigDecimal;

public interface CustomerService {
    AddCustomerResponse addCustomer(AddCustomerRequest request);

    BigDecimal getSumInCurrency(long customerId, Currency currency);
}
