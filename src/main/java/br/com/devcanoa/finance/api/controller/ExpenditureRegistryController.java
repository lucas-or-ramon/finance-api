package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.mapper.request.RequestRegistryMapper;
import br.com.devcanoa.finance.api.mapper.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import br.com.devcanoa.finance.api.service.RegistryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/expenditure")
public class ExpenditureRegistryController extends AbstractRegistryController<Expenditure> {

    public ExpenditureRegistryController(final MongoRepository<Expenditure> mongoRepository,
                                         final RegistryResponseMapper<Expenditure> responseMapper,
                                         final RequestRegistryMapper<Expenditure> requestMapper,
                                         final RegistryService<Expenditure> registryService) {
        super(mongoRepository, responseMapper, requestMapper, registryService);
    }
}
