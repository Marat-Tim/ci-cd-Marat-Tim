package ru.marattim.accounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marattim.accounts.dto.TransferRequest;
import ru.marattim.accounts.service.TransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("transfers")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestBody TransferRequest request) {
        transferService.transfer(request.getReceiverAccount(), request.getSenderAccount(),
                request.getAmountInSenderCurrency());
    }
}
