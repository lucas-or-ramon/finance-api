package br.com.devcanoa.finance.api.domain.adapter.service;

import br.com.devcanoa.finance.api.domain.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.dto.response.ResumeResponse;
import br.com.devcanoa.finance.api.domain.adapter.mapper.request.RequestRegistryMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.domain.port.service.RegistryService;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import br.com.devcanoa.finance.api.domain.port.repository.RegistryRepository;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.List;

public class RegistryServiceImpl<T extends Registry> implements RegistryService {

    private final RegistryRepository<T> repository;
    private final RequestRegistryMapper<T> requestMapper;
    private final RegistryResponseMapper<T> responseMapper;

    public RegistryServiceImpl(RegistryRepository<T> repository,
                               RequestRegistryMapper<T> requestMapper,
                               RegistryResponseMapper<T> responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<RegistryResponse> getByDescription(String description) {
        return repository.getByDescription(description).stream().map(responseMapper).toList();
    }

    @Override
    public RegistryResponse getById(ObjectId id) {
        return responseMapper.apply(repository.getById(id));
    }

    @Override
    public List<RegistryResponse> getByDate(LocalDate date) {
        return repository.getByDate(date).stream().map(responseMapper).toList();
    }

    @Override
    public void insert(final RegistryRequest request) {
        repository.save(requestMapper.mapper(Pair.of(new ObjectId(), request)));
    }

    @Override
    public void update(ObjectId id, RegistryRequest request) {
        repository.save(requestMapper.mapper(Pair.of(id, request)));
    }

    @Override
    public void delete(ObjectId id) {
        repository.delete(id);
    }

//    public ResumeResponse getResume(final LocalDate date) {
//        return resumeMapper.apply(repository.getByDate(date));
//    }
}
