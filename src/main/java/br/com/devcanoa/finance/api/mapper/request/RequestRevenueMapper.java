package br.com.devcanoa.finance.api.mapper.request;

import br.com.devcanoa.finance.api.domain.request.RegistryRequest;
import br.com.devcanoa.finance.api.model.Revenue;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class RequestRevenueMapper implements RequestRegistryMapper<Revenue> {

    @Override
    public Revenue mapper(final Pair<ObjectId, RegistryRequest> requestPair) {
        final var second = requestPair.getSecond();
        final var recurrency = new RequestRecurrencyMapper().apply(second.recurrency());
        return new Revenue(requestPair.getFirst(), second.date(), second.value(), second.description(), second.creditCardId(), recurrency);
    }
}
