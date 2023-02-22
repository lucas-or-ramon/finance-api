package br.com.devcanoa.finance.api.mapper.registry;

import br.com.devcanoa.finance.api.model.Recurrency;
import br.com.devcanoa.finance.api.model.Registry;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public interface RegistryMapper<T extends Registry> {

    T mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency);
}
