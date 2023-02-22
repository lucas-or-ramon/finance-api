package br.com.devcanoa.finance.api.mapper.response;

import br.com.devcanoa.finance.api.domain.response.RecurrencyResponse;
import br.com.devcanoa.finance.api.model.Recurrency;

import java.util.function.Function;

public class RecurrencyResponseMapper implements Function<Recurrency, RecurrencyResponse> {

    @Override
    public RecurrencyResponse apply(Recurrency recurrency) {
        return new RecurrencyResponse(recurrency.id().toString(), recurrency.type(), recurrency.start(), recurrency.end(), recurrency.paid(), recurrency.total());
    }
}
