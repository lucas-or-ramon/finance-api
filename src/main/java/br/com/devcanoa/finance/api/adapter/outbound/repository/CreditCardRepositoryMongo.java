package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.CreditCardEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.CreditCardException;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class CreditCardRepositoryMongo implements CreditCardRepository {

    private final MongoTemplate mongoTemplate;

    public CreditCardRepositoryMongo(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CreditCard> listAll() {
        try {
            final var creditCards = mongoTemplate.findAll(CreditCardEntity.class);
            return creditCards.stream().map(CreditCardEntity::mapToDomain).toList();
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card:", e);
        }
    }

    @Override
    public Optional<CreditCard> getById(final String id) {
        try {
            final var creditCard = mongoTemplate.findById(id, CreditCardEntity.class);
            return ofNullable(creditCard).map(CreditCardEntity::mapToDomain);
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to find credit card by id: " + id, e);
        }
    }

    @Override
    public Optional<CreditCard> save(final CreditCard entity) {
        try {
            final var creditCardEntity = CreditCardEntity.mapToEntity(entity);
            final var creditCard = mongoTemplate.save(creditCardEntity);
            return Optional.of(creditCard).map(CreditCardEntity::mapToDomain);
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to save credit card: " + entity.toString(), e);
        }
    }

    @Override
    public Optional<CreditCard> delete(final String id) {
        try {
            final var query = Query.query(Criteria.where("id").is(id));
            final var creditCard = mongoTemplate.findAndRemove(query, CreditCardEntity.class);
            return ofNullable(creditCard).map(CreditCardEntity::mapToDomain);
        } catch (final Exception e) {
            throw new CreditCardException("Error when trying to delete credit card by id: " + id, e);
        }
    }
}
