package br.com.devcanoa.finance.api.domain.adapter.mapper.registry;

import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public interface RegistryMapper<T extends Registry> {

    T mapper(ObjectId id, LocalDate date, Double value, String description, ObjectId creditCardId, Recurrency recurrency);
}
