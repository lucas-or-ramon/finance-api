package br.com.devcanoa.finance.api.adapter.inbound.dto;

import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Monthly;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.model.recurrence.Installments;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public final class Response {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AnnualDto(double balance, double totalRevenue, double totalExpenditure, List<Monthly> monthlyResumes) {
        public static AnnualDto mapToResponse(final List<Monthly> monthlyResumes) {
            final var totalRevenue = monthlyResumes.stream().mapToDouble(Monthly::totalRevenue).sum();
            final var totalExpenditure = monthlyResumes.stream().mapToDouble(Monthly::totalExpenditure).sum();
            final var balance = totalRevenue - totalExpenditure;
            return new AnnualDto(balance, totalRevenue, totalExpenditure, monthlyResumes);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record MonthlyDto(FinanceDate date, double balance, double totalRevenue, double totalExpenditure,
                             List<RegistryDto> revenues, List<RegistryDto> expenditures,
                             List<CreditCardDto> creditCards) {
        public static MonthlyDto mapToResponse(final Monthly monthly) {
            return new MonthlyDto(
                    monthly.date(),
                    monthly.balance(),
                    monthly.totalRevenue(),
                    monthly.totalExpenditure(),
                    monthly.revenues().stream().map(RegistryDto::mapToResponse).toList(),
                    monthly.expenditures().stream().map(RegistryDto::mapToResponse).toList(),
                    monthly.creditCards().stream().map(CreditCardDto::mapToResponse).toList());
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RegistryDto(String id, Double value, String description, String category, String creditCard, RecurrenceDto recurrence) {
        public static RegistryDto mapToResponse(final Registry registry) {
            return new RegistryDto(
                    registry.getId(),
                    registry.getValue(),
                    registry.getDescription(),
                    registry.getCategory(),
                    registry.getCreditCardId(),
                    RecurrenceDto.mapToResponse(registry.getRecurrence())
            );
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RecurrenceDto(RecurrenceType type, int dueDate, FinanceDate start, FinanceDate end, Installments installments) {
        public static RecurrenceDto mapToResponse(final Recurrence recurrence) {
            return new RecurrenceDto(
                    recurrence.type(),
                    recurrence.dueDate(),
                    recurrence.start(),
                    recurrence.end(),
                    recurrence.installments());
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CreditCardDto(String id, String name, int dueDate) {
        public static CreditCardDto mapToResponse(final CreditCard creditCard) {
            return new CreditCardDto(creditCard.id(), creditCard.name(), creditCard.dueDate());
        }
    }
}
