package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.request.RegistryRequestMapper;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/revenue")
public class RevenueRegistryController extends AbstractRegistryController<Revenue> {

    public RevenueRegistryController(final MongoRepository<Revenue> mongoRepository,
                                     final RegistryResponseMapper<Revenue> responseMapper,
                                     final RegistryRequestMapper<Revenue> requestMapper) {
        super(mongoRepository, responseMapper, requestMapper);
    }
}
