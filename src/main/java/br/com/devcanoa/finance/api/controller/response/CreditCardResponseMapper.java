package br.com.devcanoa.finance.api.controller.response;

import br.com.devcanoa.finance.api.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public class CreditCardResponseMapper implements Function<Map.Entry<Double, CreditCard>, CreditCardResponse> {

    @Override
    public CreditCardResponse apply(final Map.Entry<Double, CreditCard> map) {
        final var creditCard = map.getValue();
        return new CreditCardResponse(
                creditCard.id().toString(),
                creditCard.name(),
                creditCard.dueDate(),
                map.getKey()
        );
    }
}
