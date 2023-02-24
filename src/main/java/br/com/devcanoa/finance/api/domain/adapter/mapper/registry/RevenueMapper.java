package br.com.devcanoa.finance.api.domain.adapter.mapper.registry;

import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;
import br.com.devcanoa.finance.api.infra.adapter.entity.Revenue;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class RevenueMapper implements RegistryMapper<Revenue> {

    @Override
    public Revenue mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency) {
        return new Revenue(id, date, value, description, creditCardId, recurrency);
    }
}
