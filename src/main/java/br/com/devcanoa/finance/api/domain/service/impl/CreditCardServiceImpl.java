package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.CreditCardException;
import br.com.devcanoa.finance.api.domain.exception.CreditCardNotFoundException;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;

import java.util.List;

import static br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper.mapToDomain;
import static br.com.devcanoa.finance.api.adapter.outbound.mapper.CreditCardEntityMapper.mapToEntity;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository repository;

    public CreditCardServiceImpl(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditCard> listAll() {
        return repository.listAll().stream()
                .map(CreditCardEntityMapper::mapToDomain)
                .toList();
    }

    @Override
    public CreditCard getById(String id) {
        return repository.getById(id)
                .map(CreditCardEntityMapper::mapToDomain)
                .orElseThrow(() -> new CreditCardNotFoundException("Credit Card Not Found"));
    }

    @Override
    public void insert(CreditCard creditCard) {
        repository.save(mapToEntity(creditCard))
                .map(CreditCardEntityMapper::mapToDomain)
                .orElseThrow(() -> new CreditCardException("Credit Card Not Saved"));
    }

    @Override
    public void update(CreditCard creditCard) {
        repository.save(mapToEntity(creditCard))
                .map(CreditCardEntityMapper::mapToDomain)
                .orElseThrow(() -> new CreditCardException("Credit Card Not Updated"));
    }

    @Override
    public void delete(String id) {
        repository.delete(id)
                .map(CreditCardEntityMapper::mapToDomain)
                .orElseThrow(() -> new CreditCardException("Credit Card Not Deleted"));
    }
}
