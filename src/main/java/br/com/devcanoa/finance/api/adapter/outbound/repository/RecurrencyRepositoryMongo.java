package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrencyEntity;
import br.com.devcanoa.finance.api.domain.exception.RecurrencyException;
import br.com.devcanoa.finance.api.domain.repository.RecurrencyRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class RecurrencyRepositoryMongo implements RecurrencyRepository {

    private final MongoTemplate mongoTemplate;

    public RecurrencyRepositoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<RecurrencyEntity> getById(String id) {
        try {
            return ofNullable(mongoTemplate.findById(id, RecurrencyEntity.class));
        } catch (final Exception e) {
            throw new RecurrencyException("Error when trying to find recurrency by id: " + id, e);
        }
    }

    @Override
    public Optional<RecurrencyEntity> save(RecurrencyEntity entity) {
        try {
            return Optional.of(mongoTemplate.save(entity));
        } catch (final Exception e) {
            throw new RecurrencyException("Error when trying to save entity: " + entity, e);
        }
    }

    @Override
    public Optional<RecurrencyEntity> delete(String id) {
        try {
            return ofNullable(mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), RecurrencyEntity.class));
        } catch (final Exception e) {
            throw new RecurrencyException("Error when trying to delete recurrency by id: " + id, e);
        }
    }
}
