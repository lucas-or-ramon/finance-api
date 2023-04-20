package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static br.com.devcanoa.finance.api.adapter.outbound.repository.query.QueryMongoRegistry.whereDescriptionIs;
import static br.com.devcanoa.finance.api.adapter.outbound.repository.query.QueryMongoRegistry.whereRecurrenceHas;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class RegistryRepositoryMongo<R extends RegistryEntity> implements RegistryRepository<R> {

    private final Class<R> tClass;
    private final MongoTemplate mongoTemplate;

    public RegistryRepositoryMongo(final MongoTemplate mongoTemplate, final Class<R> tClass) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
    }

    @Override
    public Optional<R> getById(final String id) {
        try {
            return ofNullable(mongoTemplate.findById(id, tClass));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry by id: " + id, e);
        }
    }

    @Override
    public List<R> getByDate(final FinanceDate date) {
        return find(whereRecurrenceHas(date));
    }

    @Override
    public List<R> getByDescription(final String description) {
        return find(whereDescriptionIs(description));
    }

    @Override
    public Optional<R> save(final R entity) {
        try {
            return of(mongoTemplate.save(entity));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to save registry: " + entity.toString(), e);
        }
    }

    @Override
    public Optional<R> delete(final String id) {
        try {
            return ofNullable(mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<R> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }
}
