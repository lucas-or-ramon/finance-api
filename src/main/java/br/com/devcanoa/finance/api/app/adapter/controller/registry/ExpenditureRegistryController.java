package br.com.devcanoa.finance.api.app.adapter.controller.registry;

import br.com.devcanoa.finance.api.domain.port.service.RegistryService;
import br.com.devcanoa.finance.api.infra.adapter.entity.Expenditure;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/expenditure")
public class ExpenditureRegistryController extends AbstractRegistryController<Expenditure> {

    public ExpenditureRegistryController(final RegistryService registryService) {
        super(registryService);
    }
}
