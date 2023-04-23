package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
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

public class RegistryRepositoryMongo<E extends RegistryEntity> implements RegistryRepository<E> {

    private final Class<E> tClass;
    private final MongoTemplate mongoTemplate;

    public RegistryRepositoryMongo(final MongoTemplate mongoTemplate, final Class<E> tClass) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
    }

    @Override
    public Optional<E> getById(final String id) {
        try {
            return ofNullable(mongoTemplate.findById(id, tClass));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry by id: " + id, e);
        }
    }

    @Override
    public List<E> getByDate(final int dateAsNumericValue) {
        return find(whereRecurrenceHas(dateAsNumericValue));
    }

    @Override
    public List<E> getByDescription(final String description) {
        return find(whereDescriptionIs(description));
    }

    @Override
    public Optional<E> save(final E entity) {
        try {
            return of(mongoTemplate.save(entity));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to save registry: " + entity.toString(), e);
        }
    }

    @Override
    public Optional<E> delete(final String id) {
        try {
            return ofNullable(mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<E> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }
}
