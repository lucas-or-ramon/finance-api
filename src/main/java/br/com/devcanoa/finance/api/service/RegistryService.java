package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.domain.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.response.ResumeResponse;
import br.com.devcanoa.finance.api.exception.FinanceException;
import br.com.devcanoa.finance.api.mapper.request.RequestRegistryMapper;
import br.com.devcanoa.finance.api.mapper.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.mapper.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;

import java.time.LocalDate;

public class RegistryService<T extends Registry> {

    private final MongoRepository<T> mongoRepository;
    private final RecurrencyService<T> recurrencyService;
    private final RegistryResponseMapper<T> registryResponseMapper;
    private final ResumeResponseMapper<T> resumeResponseMapper;
    private final RequestRegistryMapper<T> requestRegistryMapper;

    public RegistryService(MongoRepository<T> mongoRepository,
                           RecurrencyService<T> recurrencyService,
                           RegistryResponseMapper<T> registryResponseMapper,
                           ResumeResponseMapper<T> resumeResponseMapper,
                           RequestRegistryMapper<T> requestRegistryMapper) {
        this.mongoRepository = mongoRepository;
        this.recurrencyService = recurrencyService;
        this.registryResponseMapper = registryResponseMapper;
        this.resumeResponseMapper = resumeResponseMapper;
        this.requestRegistryMapper = requestRegistryMapper;
    }

    public ResumeResponse getResume(final LocalDate date) {
        return resumeResponseMapper.apply(mongoRepository.getByDate(date));
    }

    public RegistryResponse insert(final RegistryRequest registryRequest) {
        final T registry = requestRegistryMapper.mapper(Pair.of(new ObjectId(), registryRequest));
        return recurrencyService.insertWithRecurrency(registry)
                .map(registryResponseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to save registry"));
    }
}
