package ru.marattim.accounts.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marattim.accounts.dto.AddCustomerRequest;
import ru.marattim.accounts.dto.AddCustomerResponse;
import ru.marattim.accounts.dto.GetSumByCurrencyResponse;
import ru.marattim.accounts.service.impl.CustomerServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AddCustomerResponse addCustomer(@Valid @RequestBody AddCustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @GetMapping("{customerId}/balance")
    @ResponseStatus(HttpStatus.OK)
    public GetSumByCurrencyResponse getSumInCurrency(@PathVariable("customerId") long customerId,
                                                     @RequestParam Currency currency) {
        return new GetSumByCurrencyResponse(customerService.getSumInCurrency(customerId, currency), currency);
    }
}
