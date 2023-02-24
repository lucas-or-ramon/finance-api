package br.com.devcanoa.finance.api.domain.adapter.mapper.request;

import br.com.devcanoa.finance.api.domain.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.infra.adapter.entity.CreditCard;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;

import java.util.function.Function;

public class CreditCardRequestMapper implements Function<Pair<ObjectId, CreditCardRequest>, CreditCard> {

    @Override
    public CreditCard apply(Pair<ObjectId, CreditCardRequest> request) {
        return new CreditCard(request.getFirst(), request.getSecond().name(), request.getSecond().dueDate());
    }
}
