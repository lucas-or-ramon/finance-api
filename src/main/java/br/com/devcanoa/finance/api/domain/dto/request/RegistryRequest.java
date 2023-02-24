package br.com.devcanoa.finance.api.domain.dto.request;

import org.bson.types.ObjectId;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record RegistryRequest(@NotBlank @Max(value = 20) String description,
                              @NotBlank @Min(value = 0L) Double value,
                              @NotBlank @Size(min = 10, max = 10) LocalDate date,
                              @NotNull RecurrencyRequest recurrency,
                              ObjectId creditCardId) {
}
