package br.com.devcanoa.finance.api.mapper.request;

import br.com.devcanoa.finance.api.domain.request.RecurrencyRequest;
import br.com.devcanoa.finance.api.model.Recurrency;
import org.bson.types.ObjectId;

import java.util.function.Function;

public class RequestRecurrencyMapper implements Function<RecurrencyRequest, Recurrency> {

    @Override
    public Recurrency apply(RecurrencyRequest request) {
        return new Recurrency(new ObjectId(), request.type(), request.start(), request.end(), request.paid(), request.total());
    }
}
