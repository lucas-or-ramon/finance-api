package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.request.RegistryRequestMapper;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.service.FinanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/expenditure")
public class ExpenditureRegistryController extends AbstractRegistryController<Expenditure> {

    public ExpenditureRegistryController(final FinanceService<Expenditure> financeService,
                                         final RegistryResponseMapper<Expenditure> responseMapper,
                                         final RegistryRequestMapper<Expenditure> requestMapper) {
        super(financeService, responseMapper, requestMapper);
    }
}
