package ru.marattim.accounts.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marattim.accounts.exception.AccountNotFoundException;
import ru.marattim.accounts.exception.NotEnoughMoneyException;
import ru.marattim.accounts.repository.AccountRepository;
import ru.marattim.accounts.service.ConverterClient;
import ru.marattim.accounts.service.TransferService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;

    private final ConverterClient converterClient;

    @Transactional
    @Override
    public void transfer(long receiverAccount, long senderAccount, BigDecimal amountInSenderCurrency) {
        var receiver = accountRepository.findByIdWithLock(receiverAccount)
                .orElseThrow(() -> new AccountNotFoundException(receiverAccount));
        var sender = accountRepository.findByIdWithLock(senderAccount)
                .orElseThrow(() -> new AccountNotFoundException(senderAccount));
        if (sender.getAmount().compareTo(amountInSenderCurrency) < 0) {
            throw new NotEnoughMoneyException();
        }
        if (receiver.getCurrency() == sender.getCurrency()) {
            sender.setAmount(sender.getAmount().subtract(amountInSenderCurrency));
            receiver.setAmount(receiver.getAmount().add(amountInSenderCurrency));
            accountRepository.saveAll(List.of(sender, receiver));
        } else {
            BigDecimal amountInReceiverCurrency =
                    converterClient.convert(sender.getCurrency(), receiver.getCurrency(), amountInSenderCurrency);
            sender.setAmount(sender.getAmount().subtract(amountInSenderCurrency));
            receiver.setAmount(receiver.getAmount().add(amountInReceiverCurrency));
            accountRepository.saveAll(List.of(sender, receiver));
        }
    }
}
