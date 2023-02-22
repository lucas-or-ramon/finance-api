package br.com.devcanoa.finance.api.mapper.registry;

import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Recurrency;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class ExpenditureMapper implements RegistryMapper<Expenditure> {

    @Override
    public Expenditure mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency) {
        return new Expenditure(id, date, value, description, creditCardId, recurrency);
    }
}
