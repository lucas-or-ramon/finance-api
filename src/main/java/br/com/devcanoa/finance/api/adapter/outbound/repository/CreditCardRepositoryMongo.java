package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.CreditCardException;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.adapter.outbound.entity.CreditCardEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper.mapToDomain;
import static br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper.mapToEntity;
import static java.util.Optional.ofNullable;

@Repository
public class CreditCardRepositoryMongo implements CreditCardRepository {

    private final MongoTemplate mongoTemplate;

    public CreditCardRepositoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CreditCard> listAll() {
        try {
            return mongoTemplate.findAll(CreditCardEntity.class).stream().map(CreditCardEntityMapper::mapToDomain).toList();
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card:", e);
        }
    }

    @Override
    public Optional<CreditCard> getById(String id) {
        try {
            return ofNullable(mapToDomain(mongoTemplate.findById(id, CreditCardEntity.class)));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card by id: " + id, e);
        }
    }

    @Override
    public Optional<CreditCard> save(CreditCard creditCard) {
        try {
            return ofNullable(mapToDomain(mongoTemplate.save(mapToEntity(creditCard))));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to save credit card: " + creditCard.toString(), e);
        }
    }

    @Override
    public Optional<CreditCard> delete(String id) {
        try {
            return ofNullable(mapToDomain(mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), CreditCardEntity.class)));
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to delete credit card by id: " + id, e);
        }
    }
}
