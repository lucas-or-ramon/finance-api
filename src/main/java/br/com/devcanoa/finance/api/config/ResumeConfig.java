package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.domain.service.AnnualResumeService;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import br.com.devcanoa.finance.api.domain.service.impl.AnnualResumeServiceImpl;
import br.com.devcanoa.finance.api.domain.service.impl.MonthlyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class ResumeConfig {

    private final CreditCardService creditCardService;
    private final RegistryService expenditureService;
    private final RegistryService revenueService;

    public ResumeConfig(final CreditCardService creditCardService,
                        final RegistryService expenditureService,
                        final RegistryService revenueService) {
        this.creditCardService = creditCardService;
        this.expenditureService = expenditureService;
        this.revenueService = revenueService;
    }

    @Bean
    public AnnualResumeService annualService(final TaskExecutor taskExecutor) {
        return new AnnualResumeServiceImpl(taskExecutor, monthlyService());
    }

    @Bean
    public MonthlyService monthlyService() {
        return new MonthlyServiceImpl(creditCardService, expenditureService, revenueService);
    }
}
