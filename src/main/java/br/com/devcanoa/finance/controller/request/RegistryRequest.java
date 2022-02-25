package br.com.devcanoa.finance.controller.request;

import br.com.devcanoa.finance.domain.Category;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

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

    public Category getCategory() {
        if (Objects.isNull(category) || category.isBlank()) return Category.OTHER;

        return Category.getCategoryByValue(category);
    }

    @Override
    public String toString() {
        return "RegistryRequest: {" + "date: " + date + ", value: " + value + ", description: " + description + ", category: " + category + "}";
    }
}
