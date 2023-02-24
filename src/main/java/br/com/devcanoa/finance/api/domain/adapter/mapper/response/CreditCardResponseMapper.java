package br.com.devcanoa.finance.api.domain.adapter.mapper.response;

import br.com.devcanoa.finance.api.domain.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.domain.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.infra.adapter.entity.CreditCard;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CreditCardResponseMapper implements Function<Map.Entry<CreditCard, List<RegistryResponse>>, CreditCardResponse> {

    @Override
    public CreditCardResponse apply(final Map.Entry<CreditCard, List<RegistryResponse>> map) {
        final var creditCard = map.getKey();
        final var registries = map.getValue();
        return new CreditCardResponse(
                creditCard.id().toString(),
                creditCard.name(),
                creditCard.dueDate(),
                registries.stream().mapToDouble(RegistryResponse::value).sum(),
                registries
        );
    }
}
