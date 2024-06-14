package ru.marattim.accounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.marattim.accounts.dto.EmptyResponse;
import ru.marattim.accounts.dto.TransferRequest;
import ru.marattim.accounts.service.TransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("transfers")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse transfer(TransferRequest request) {
        transferService.transfer(request.getReceiverAccount(), request.getSenderAccount(),
                request.getAmountInSenderCurrency());
        return EmptyResponse.INSTANCE;
    }
}
