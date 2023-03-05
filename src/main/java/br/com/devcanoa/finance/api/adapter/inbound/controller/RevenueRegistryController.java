package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.domain.model.Revenue;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/revenue")
public class RevenueRegistryController extends AbstractRegistryController<Revenue> {

    public RevenueRegistryController(final RegistryService<Revenue> revenueService,
                                     final RegistryMapper<Revenue> revenueMapper) {
        super(revenueService, revenueMapper);
    }
}
