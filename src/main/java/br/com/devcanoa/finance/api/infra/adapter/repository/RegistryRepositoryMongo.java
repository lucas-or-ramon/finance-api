package br.com.devcanoa.finance.api.infra.adapter.repository;

import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.port.repository.RegistryRepository;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

import static br.com.devcanoa.finance.api.infra.adapter.repository.query.QueryMongoRegistry.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class RegistryRepositoryMongo<T extends Registry> implements RegistryRepository<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> tClass;

    public RegistryRepositoryMongo(final MongoTemplate mongoTemplate, final Class<T> tClass) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = tClass;
    }

    @Override
    public T getById(final ObjectId id) {
        try {
            return mongoTemplate.findById(id, tClass);
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

//    @Override
//    public void save(final T registry) {
//        if (exist(registry)) {
//            throw new RegistryAlreadyExistsException("Expenditure already exists: " + registry.getId());
//        }
//        return Optional.of(save(registry));
//    }

    @Override
    public void save(final T registry) {
        try {
            mongoTemplate.save(registry);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to save registry: " + registry.toString(), e);
        }
    }

    @Override
    public boolean exist(final T registry) {
        try {
            return mongoTemplate.exists(whereRegistryIs(registry), tClass);
        } catch (final Exception e) {
            throw new RegistryException("Erro when trying to find registry: " + registry.toString(), e);
        }
    }

    @Override
    public void delete(final ObjectId id) {
        try {
            mongoTemplate.findAndRemove(Query.query(where("id").is(id)), tClass);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to delete registry by id: " + id, e);
        }
    }

    private List<T> find(final Query query) {
        try {
            return mongoTemplate.find(query, tClass);
        } catch (final Exception e) {
            throw new RegistryException("Error when trying to find registry with query: " + query.toString(), e);
        }
    }
}
