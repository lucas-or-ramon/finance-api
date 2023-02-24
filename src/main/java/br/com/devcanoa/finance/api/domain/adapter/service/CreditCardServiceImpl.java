package br.com.devcanoa.finance.api.domain.adapter.service;

import br.com.devcanoa.finance.api.domain.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.domain.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.domain.adapter.mapper.request.CreditCardRequestMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.response.CreditCardResponseMapper;
import br.com.devcanoa.finance.api.domain.port.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.port.service.CreditCardService;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository repository;
    private final CreditCardRequestMapper requestMapper;
    private final CreditCardResponseMapper responseMapper;

    public CreditCardServiceImpl(CreditCardRepository repository,
                                 CreditCardRequestMapper requestMapper,
                                 CreditCardResponseMapper responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<CreditCardResponse> listAll() {
        return repository.listAll().stream().map(c -> responseMapper.apply(Map.entry(c, Collections.emptyList()))).toList();
    }

    @Override
    public CreditCardResponse getById(ObjectId id) {
        return responseMapper.apply(Map.entry(repository.getById(id), Collections.emptyList()));
    }

    @Override
    public void insert(CreditCardRequest creditCardRequest) {
        repository.save(requestMapper.apply(Pair.of(new ObjectId(), creditCardRequest)));
    }

    @Override
    public void update(ObjectId id, CreditCardRequest creditCardRequest) {
        repository.save(requestMapper.apply(Pair.of(id, creditCardRequest)));
    }

    @Override
    public void delete(ObjectId id) {
        repository.delete(id);
    }
}
