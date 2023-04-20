package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RegistryRequest(@NotBlank @Max(value = 20) String description,
                              @NotBlank @Min(value = 0L) Double value,
                              String creditCardId,
                              @NotNull RecurrenceRequest recurrence) {
}
