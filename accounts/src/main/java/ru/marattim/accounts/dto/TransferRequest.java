package ru.marattim.accounts.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull
    private long receiverAccount;

    @NotNull
    private long senderAccount;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amountInSenderCurrency;
}
