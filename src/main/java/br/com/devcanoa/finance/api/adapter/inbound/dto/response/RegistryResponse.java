package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import br.com.devcanoa.finance.api.domain.model.CreditCard;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegistryResponse(String id, Double value, String description, String category, CreditCard creditCard,
                               RecurrenceResponse recurrence) {
}
