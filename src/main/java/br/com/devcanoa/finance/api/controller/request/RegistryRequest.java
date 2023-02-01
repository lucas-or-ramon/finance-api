package br.com.devcanoa.finance.api.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record RegistryRequest(@NotBlank @Max(value = 20) String description,
                              @NotBlank @Min(value = 0L) Double value,
                              @NotBlank @Size(min = 10, max = 10) LocalDate date,
                              String category) {}
