package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.model.Registry;
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

public class RegistryRepositoryMongo implements RegistryRepository {

    private final MongoTemplate mongoTemplate;
    private final Class<? extends RegistryEntity> tClass;
    private final RegistryEntityMapper<? extends RegistryEntity> registryEntityMapper;

    public RegistryRepositoryMongo(final MongoTemplate mongoTemplate,
                                   final Class<? extends RegistryEntity> tClass,
                                   final RegistryEntityMapper<? extends RegistryEntity> registryEntityMapper) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
        this.registryEntityMapper = registryEntityMapper;
    }

    @Override
    public Optional<Registry> getById(final String id) {
        try {
            return ofNullable(mongoTemplate.findById(id, tClass)).map(RegistryEntity::mapToDomain);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry by id: " + id, e);
        }
    }

    @Override
    public List<Registry> getByDate(final int dateAsNumericValue) {
        return find(whereRecurrenceHas(dateAsNumericValue));
    }

    @Override
    public List<Registry> getByDescription(final String description) {
        return find(whereDescriptionIs(description));
    }

    @Override
    public Optional<Registry> save(final Registry entity) {
        try {
            return of(mongoTemplate.save(registryEntityMapper.mapToEntity(entity))).map(RegistryEntity::mapToDomain);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to save registry: " + entity.toString(), e);
        }
    }

    @Override
    public Optional<Registry> delete(final String id) {
        try {
            return ofNullable(mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass)).map(RegistryEntity::mapToDomain);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<Registry> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass).stream().map(RegistryEntity::mapToDomain).toList();
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }
}
