package br.com.devcanoa.finance.api.infra.adapter.repository;

import br.com.devcanoa.finance.api.domain.exception.CreditCardNotFoundException;
import br.com.devcanoa.finance.api.domain.port.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.infra.adapter.entity.CreditCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CreditCardRepositoryMongo implements CreditCardRepository {

    private final MongoTemplate mongoTemplate;

    public CreditCardRepositoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CreditCard> listAll() {
        return mongoTemplate.findAll(CreditCard.class);
    }

    @Override
    public CreditCard getById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findById(id, CreditCard.class))
                .orElseThrow(() -> new CreditCardNotFoundException("Credit Card Not Found"));
    }

    @Override
    public void save(CreditCard creditCard) {
        mongoTemplate.save(creditCard);
    }

    @Override
    public void delete(ObjectId id) {
        mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), CreditCard.class);
    }
}
