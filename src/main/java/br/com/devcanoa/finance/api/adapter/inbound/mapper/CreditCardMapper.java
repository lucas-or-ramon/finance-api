package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.domain.model.CreditCard;

public interface CreditCardMapper {

    static CreditCardResponse mapToResponse(final CreditCard creditCard) {
        return new CreditCardResponse(creditCard.id(), creditCard.name(), creditCard.dueDate());
    }

    static CreditCard mapToDomain(final String id, final CreditCardRequest request) {
        return new CreditCard(id, request.name(), request.dueDate());
    }
}
