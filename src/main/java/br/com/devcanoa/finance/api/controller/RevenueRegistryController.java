package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.request.RegistryRequestMapper;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.service.FinanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/revenue")
public class RevenueRegistryController extends AbstractRegistryController<Revenue> {

    public RevenueRegistryController(final FinanceService<Revenue> financeService,
                                     final RegistryResponseMapper<Revenue> responseMapper,
                                     final RegistryRequestMapper<Revenue> requestMapper) {
        super(financeService, responseMapper, requestMapper);
    }
}
