package br.com.devcanoa.finance.controller.response;

import br.com.devcanoa.finance.model.Expenditure;
import br.com.devcanoa.finance.model.Revenue;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RegistryResponse {
    private final String id;
    private final LocalDate date;
    private final Double value;
    private final String description;
    private String category;

    private RegistryResponse(Revenue revenue) {
        this.id = revenue.getId().toString();
        this.date = revenue.getDate();
        this.value = revenue.getValue();
        this.description = revenue.getDescription();
    }

    private RegistryResponse(Expenditure expenditure) {
        this.id = expenditure.getId().toString();
        this.date = expenditure.getDate();
        this.value = expenditure.getValue();
        this.description = expenditure.getDescription();
        this.category = expenditure.getCategory().getValue();
    }

    public static RegistryResponse fromRevenue(Revenue revenue) {
        return new RegistryResponse(revenue);
    }

    public static RegistryResponse fromExpenditure(Expenditure expenditure) {
        return new RegistryResponse(expenditure);
    }

    public static List<RegistryResponse> fromRevenueList(List<Revenue> revenues) {
        return revenues.stream().map(RegistryResponse::fromRevenue).collect(Collectors.toList());
    }

    public static List<RegistryResponse> fromExpenditureList(List<Expenditure> expenditures) {
        return expenditures.stream().map(RegistryResponse::fromExpenditure).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
