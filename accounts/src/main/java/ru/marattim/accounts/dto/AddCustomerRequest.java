package ru.marattim.accounts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.marattim.accounts.validation.BeforeNow;

import java.time.LocalDate;

@Data
public class AddCustomerRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @BeforeNow(message =
            "Разница между текущей датой и датой рождения должна быть в диапазоне между ${minYears} и ${maxYears}",
            minYears = 14, maxYears = 120)
    private LocalDate birthDay;
}
