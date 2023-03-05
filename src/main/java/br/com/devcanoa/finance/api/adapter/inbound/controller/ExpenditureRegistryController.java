package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/expenditure")
public class ExpenditureRegistryController extends AbstractRegistryController<Expenditure> {

    public ExpenditureRegistryController(final RegistryService<Expenditure> expenditureService,
                                         final RegistryMapper<Expenditure> expenditureMapper) {
        super(expenditureService, expenditureMapper);
    }
}
