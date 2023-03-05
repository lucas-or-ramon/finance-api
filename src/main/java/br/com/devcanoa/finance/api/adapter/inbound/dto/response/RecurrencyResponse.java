package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import br.com.devcanoa.finance.api.domain.model.recurrency.RecurrencyType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecurrencyResponse(String id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {
}
