package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistryRequest(@NotBlank String description,
                              @NotNull @Min(0) Double value,
                              String creditCardId,
                              @NotNull RecurrenceRequest recurrence) {
}
