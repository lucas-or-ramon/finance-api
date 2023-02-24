package br.com.devcanoa.finance.api.domain.adapter.mapper.response;

import br.com.devcanoa.finance.api.domain.dto.response.RecurrencyResponse;
import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;

import java.util.function.Function;

public class RecurrencyResponseMapper implements Function<Recurrency, RecurrencyResponse> {

    @Override
    public RecurrencyResponse apply(Recurrency recurrency) {
        return new RecurrencyResponse(recurrency.id().toString(), recurrency.type(), recurrency.start(), recurrency.end(), recurrency.paid(), recurrency.total());
    }
}
