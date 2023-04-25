package br.com.devcanoa.finance.api.adapter.inbound.dto;

import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class Request {

    public record RegistryDto(@NotBlank String category, @NotBlank String description, @NotNull @Min(0) Double value,
                              @NotNull RecurrenceDto recurrence, String creditCardId) {}

    public record RecurrenceDto(@NotNull RecurrenceType type, @Min(1) @Max(31) int dueDate,
                                 @NotNull FinanceDateDto start, @NotNull FinanceDateDto end) {
        public Recurrence mapToDomain() {
            return Recurrence.builder()
                    .type(type)
                    .dueDate(dueDate)
                    .start(start.mapToDomain())
                    .end(end.mapToDomain())
                    .build();
        }
    }

    public record FinanceDateDto(@Min(2000) @Max(9999) int year, @Min(1) @Max(12) int month) {
        public FinanceDate mapToDomain() {
            return new FinanceDate(year, month);
        }
        public FinanceDateDto minusMonths(final int i) {
            if (i < 0 || i > 11) {
                throw new IllegalArgumentException("i must be between 0 and 11");
            }
            return (month - i < 1)
                    ? new FinanceDateDto(year - 1, 12 - (i - month))
                    : new FinanceDateDto(year, month - i);
        }
    }

    public record CreditCardDto(@NotBlank String name, @Min(1) @Max(31) int dueDate) {
        public CreditCard mapToDomain(final String id) {
            return new CreditCard(id, name, dueDate);
        }
    }
}
