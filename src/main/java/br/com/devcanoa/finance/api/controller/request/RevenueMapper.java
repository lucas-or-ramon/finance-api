package br.com.devcanoa.finance.api.controller.request;

import br.com.devcanoa.finance.api.model.Revenue;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class RevenueMapper implements RegistryRequestMapper<Revenue> {

    @Override
    public Revenue mapper(final Pair<ObjectId, RegistryRequest> requestPair) {
        final var second = requestPair.getSecond();
        return new Revenue(requestPair.getFirst(), second.date(), second.value(), second.description(), second.creditCardId());
    }
}
