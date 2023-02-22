package br.com.devcanoa.finance.api.mapper.registry;

import br.com.devcanoa.finance.api.model.Recurrency;
import br.com.devcanoa.finance.api.model.Revenue;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class RevenueMapper implements RegistryMapper<Revenue> {

    @Override
    public Revenue mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency) {
        return new Revenue(id, date, value, description, creditCardId, recurrency);
    }
}
