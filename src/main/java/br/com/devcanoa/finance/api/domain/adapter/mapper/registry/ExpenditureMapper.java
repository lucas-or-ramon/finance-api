package br.com.devcanoa.finance.api.domain.adapter.mapper.registry;

import br.com.devcanoa.finance.api.infra.adapter.entity.Expenditure;
import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class ExpenditureMapper implements RegistryMapper<Expenditure> {

    @Override
    public Expenditure mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency) {
        return new Expenditure(id, date, value, description, creditCardId, recurrency);
    }
}
