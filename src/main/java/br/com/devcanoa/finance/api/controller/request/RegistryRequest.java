package br.com.devcanoa.finance.api.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class RegistryRequest {

    @NotBlank
    @Max(value = 20)
    private String description;

    @NotBlank
    @Min(value = 0L)
    private Double value;

    @NotBlank
    @Size(min = 10, max = 10)
    private LocalDate date;

    private String category;

    public RegistryRequest() {}

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "RegistryRequest: {" + "date: " + date + ", value: " + value + ", description: " + description + ", category: " + category + "}";
    }
}
