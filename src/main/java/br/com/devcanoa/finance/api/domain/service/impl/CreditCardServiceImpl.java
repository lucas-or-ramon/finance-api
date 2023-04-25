package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import org.bson.types.ObjectId;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository repository;

    public CreditCardServiceImpl(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditCard> listAll() {
        return repository.listAll().stream().toList();
    }

    @Override
    public Either<String, CreditCard> getById(final String id) {
        return repository.getById(id)
                .map(Either::<String, CreditCard>success)
                .orElse(Either.failure("Credit Card Not Found"));
    }

    @Override
    public Either<String, CreditCard> insert(final Request.CreditCardDto creditCard) {
        return repository.save(creditCard.mapToDomain(new ObjectId().toString()))
                .map(Either::<String, CreditCard>success)
                .orElse(Either.failure("Credit Card Not Inserted"));
    }

    @Override
    public Either<String, CreditCard> update(final String id, final Request.CreditCardDto creditCard) {
        return repository.save(creditCard.mapToDomain(id))
                .map(Either::<String, CreditCard>success)
                .orElse(Either.failure("Credit Card Not Updated"));
    }

    @Override
    public Either<String, CreditCard> delete(String id) {
        return repository.delete(id)
                .map(Either::<String, CreditCard>success)
                .orElse(Either.failure("Credit Card Not Deleted"));
    }
}
