package br.com.devcanoa.finance.api.repository;

import br.com.devcanoa.finance.api.model.Registry;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MongoRepository<T extends Registry> {

    List<T> getByDescription(String description);

    Optional<T> getById(ObjectId id);

    List<T> getByDate(LocalDate date);

    Optional<T> insert(T registry);

    boolean exist(T registry);

    Optional<T> update(T registry);

    Optional<T> delete(ObjectId id);
}
