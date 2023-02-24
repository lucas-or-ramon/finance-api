package br.com.devcanoa.finance.api.domain.adapter.mapper.request;

import br.com.devcanoa.finance.api.domain.dto.request.RecurrencyRequest;
import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;
import org.bson.types.ObjectId;

import java.util.function.Function;

public class RequestRecurrencyMapper implements Function<RecurrencyRequest, Recurrency> {

    @Override
    public Recurrency apply(RecurrencyRequest request) {
        return new Recurrency(new ObjectId(), request.type(), request.start(), request.end(), request.paid(), request.total());
    }
}
