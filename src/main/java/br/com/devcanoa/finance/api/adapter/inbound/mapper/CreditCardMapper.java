package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.domain.model.CreditCard;

public final class CreditCardMapper {

    private CreditCardMapper() {}

    public static CreditCardResponse mapToResponse(final CreditCard creditCard) {
        return new CreditCardResponse(creditCard.id(), creditCard.name(), creditCard.dueDate());
    }

    public static CreditCard mapToDomain(final String id, final CreditCardRequest request) {
        return new CreditCard(id, request.name(), request.dueDate());
    }
}
