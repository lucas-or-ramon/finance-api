package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.adapter.inbound.mapper.ExpenditureMapper;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.MonthlyMapper;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RevenueMapper;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import br.com.devcanoa.finance.api.domain.model.Revenue;
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
    private final RegistryService<Expenditure> expenditureService;
    private final RegistryService<Revenue> revenueService;

    public ResumeConfig(final CreditCardService creditCardService,
                        final RegistryService<Expenditure> expenditureService,
                        final RegistryService<Revenue> revenueService) {
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

    @Bean
    public MonthlyMapper monthlyMapper(final RegistryMapper<Revenue> revenueMapper,
                                       final RegistryMapper<Expenditure> expenditureMapper) {
        return new MonthlyMapper(revenueMapper, expenditureMapper);
    }
}
