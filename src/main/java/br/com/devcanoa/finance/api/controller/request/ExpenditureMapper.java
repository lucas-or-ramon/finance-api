package br.com.devcanoa.finance.api.controller.request;

import br.com.devcanoa.finance.api.model.Expenditure;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class ExpenditureMapper implements RegistryRequestMapper<Expenditure> {

    @Override
    public Expenditure mapper(final Pair<ObjectId, RegistryRequest> requestPair) {
        final var second = requestPair.getSecond();
        return new Expenditure(requestPair.getFirst(), second.date(), second.value(), second.description());
    }
}