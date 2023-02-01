package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface FinanceService<T extends Registry> extends MongoRepository<T> {
}
