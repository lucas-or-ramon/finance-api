package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static br.com.devcanoa.finance.api.adapter.outbound.repository.query.QueryMongoRegistry.whereDateIs;
import static br.com.devcanoa.finance.api.adapter.outbound.repository.query.QueryMongoRegistry.whereDescriptionIs;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class RegistryRepositoryMongo<R extends RegistryEntity, T extends Registry> implements RegistryRepository<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<R> tClass;
    private final RegistryEntityMapper<R, T> mapper;

    public RegistryRepositoryMongo(final MongoTemplate mongoTemplate, final Class<R> tClass, final RegistryEntityMapper<R, T> mapper) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
        this.mapper = mapper;
    }

    @Override
    public Optional<T> getById(final String id) {
        try {
            return Optional.ofNullable(mapper.mapToDomain(mongoTemplate.findById(id, tClass)));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry by id: " + id, e);
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
    public Optional<T> save(final T registry) {
        try {
            return Optional.of(mapper.mapToDomain(mongoTemplate.save(mapper.mapToEntity(registry))));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to save registry: " + registry.toString(), e);
        }
    }

    @Override
    public Optional<T> delete(final String id) {
        try {
            return Optional.ofNullable(mapper.mapToDomain(mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass)));
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<T> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass).stream().map(mapper::mapToDomain).toList();
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }
}
