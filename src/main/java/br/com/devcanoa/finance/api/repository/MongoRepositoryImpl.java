package br.com.devcanoa.finance.api.repository;

import br.com.devcanoa.finance.api.exception.FinanceException;
import br.com.devcanoa.finance.api.exception.RegistryAlreadyExistsException;
import br.com.devcanoa.finance.api.model.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static br.com.devcanoa.finance.api.repository.QueryMongoRegistry.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoRepositoryImpl<T extends Registry> implements MongoRepository<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> tClass;

    public MongoRepositoryImpl(final MongoTemplate mongoTemplate, final Class<T> tClass) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
    }

    @Override
    public Optional<T> getById(final ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, tClass));
        } catch (final Exception e) {
            throw new FinanceException("Error when trying to find registry by id: " + id, e);
        }
    }

    @Override
    public List<T> getByDate(final LocalDate date) {
        return find(whereDateIs(date));
    }

    @Override
    public List<T> getByDescription(final String description) {
        return find(whereDescriptionIs(description));
    }

    @Override
    public Optional<T> insert(final T registry) {
        if (exist(registry)) {
            throw new RegistryAlreadyExistsException("Expenditure already exists: " + registry.getId());
        }
        return Optional.of(save(registry));
    }

    @Override
    public boolean exist(final T registry) {
        try {
            return mongoTemplate.exists(whereRegistryIs(registry), tClass);
        } catch (final Exception e) {
            throw new FinanceException("Erro when trying to find registry: " + registry.toString(), e);
        }
    }

    @Override
    public Optional<T> update(final T registry) {
        return getById(registry.getId()).map(r -> save(registry));
    }

    @Override
    public Optional<T> delete(final ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass));
        } catch (final Exception e) {
            throw new FinanceException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<T> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass);
        } catch (final Exception e) {
            throw new FinanceException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }

    private T save(final T registry) {
        try {
            return mongoTemplate.save(registry);
        } catch (final Exception e) {
            throw new FinanceException("Error when trying to save registry: " + registry.toString(), e);
        }
    }
}
