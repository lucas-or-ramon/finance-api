package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.ResumeResponse;
import br.com.devcanoa.finance.api.controller.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistryService<T extends Registry> {

    private final MongoRepository<T> mongoRepository;
    private final ResumeResponseMapper<T> resumeResponseMapper;

    public RegistryService(MongoRepository<T> mongoRepository, ResumeResponseMapper<T> resumeResponseMapper) {
        this.mongoRepository = mongoRepository;
        this.resumeResponseMapper = resumeResponseMapper;
    }

    public ResumeResponse getResume(final LocalDate date) {
        return resumeResponseMapper.apply(mongoRepository.getByDate(date));
    }
}
