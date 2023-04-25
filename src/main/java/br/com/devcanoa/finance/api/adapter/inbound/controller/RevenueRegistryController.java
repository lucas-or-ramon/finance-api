package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/revenue")
public class RevenueRegistryController extends AbstractRegistryController {

    public RevenueRegistryController(final RegistryService revenueService) {
        super(revenueService);
    }
}
