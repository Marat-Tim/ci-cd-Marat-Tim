package ru.marattim.accounts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.openapitools.model.Currency;

@Data
public class AddAccountRequest {
    @NotNull
    private long customerId;

    @NotNull
    private Currency currency;
}
