package br.com.devcanoa.finance.api.controller.response;

import br.com.devcanoa.finance.api.model.CreditCard;
import br.com.devcanoa.finance.api.model.Registry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
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
