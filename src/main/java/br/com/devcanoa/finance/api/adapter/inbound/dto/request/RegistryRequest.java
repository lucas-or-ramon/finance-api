package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record RegistryRequest(@NotBlank @Max(value = 20) String description, @NotBlank @Min(value = 0L) Double value,
                              @NotBlank @Size(min = 10, max = 10) LocalDate date, @NotNull RecurrencyRequest recurrency,
                              String creditCardId) {
}
