package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import br.com.devcanoa.finance.api.domain.model.recurrency.RecurrencyType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record RecurrencyRequest(String id, @NotNull RecurrencyType type, LocalDate start, LocalDate end, Integer paid,
                                Integer total) {
}
