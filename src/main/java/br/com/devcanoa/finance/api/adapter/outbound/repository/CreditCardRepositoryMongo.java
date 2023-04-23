package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.CreditCardEntity;
import br.com.devcanoa.finance.api.domain.exception.CreditCardException;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class CreditCardRepositoryMongo implements CreditCardRepository {

    private final MongoTemplate mongoTemplate;

    public CreditCardRepositoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CreditCardEntity> listAll() {
        try {
            return mongoTemplate.findAll(CreditCardEntity.class);
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card:", e);
        }
    }

    @Override
    public Optional<CreditCardEntity> getById(String id) {
        try {
            return ofNullable(mongoTemplate.findById(id, CreditCardEntity.class));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card by id: " + id, e);
        }
    }

    @Override
    public Optional<CreditCardEntity> save(CreditCardEntity entity) {
        try {
            return Optional.of(mongoTemplate.save(entity));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to save credit card: " + entity.toString(), e);
        }
    }

    @Override
    public Optional<CreditCardEntity> delete(String id) {
        try {
            return ofNullable(mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), CreditCardEntity.class));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to delete credit card by id: " + id, e);
        }
    }
}
