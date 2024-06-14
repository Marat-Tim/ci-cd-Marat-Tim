package ru.marattim.accounts.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marattim.accounts.dto.*;
import ru.marattim.accounts.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AddAccountResponse addAccount(@Valid @RequestBody AddAccountRequest request) {
        return new AddAccountResponse(accountService.addAccount(request));
    }

    @GetMapping("{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountResponse getAccount(@PathVariable("accountNumber") long accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @PostMapping("{accountNumber}/top-up")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse topUp(@PathVariable("accountNumber") long accountNumber,
                               @Valid @RequestBody TopUpRequest request) {
        accountService.topUp(accountNumber, request.getAmount());
        return EmptyResponse.INSTANCE;
    }
}
