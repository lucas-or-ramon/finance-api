package br.com.devcanoa.finance.api.infra.config;

import br.com.devcanoa.finance.api.domain.adapter.mapper.request.RequestExpenditureMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.request.RequestRevenueMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.domain.adapter.service.AnnualResumeServiceImpl;
import br.com.devcanoa.finance.api.domain.adapter.service.MonthlyResumeServiceImpl;
import br.com.devcanoa.finance.api.domain.adapter.service.RegistryServiceImpl;
import br.com.devcanoa.finance.api.domain.port.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.port.service.AnnualResumeService;
import br.com.devcanoa.finance.api.domain.port.service.MonthlyResumeService;
import br.com.devcanoa.finance.api.infra.adapter.entity.Expenditure;
import br.com.devcanoa.finance.api.infra.adapter.entity.Revenue;
import br.com.devcanoa.finance.api.infra.adapter.repository.RegistryRepositoryMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ResumeConfig {

    private final TaskExecutor taskExecutor;
    private final MongoTemplate mongoTemplate;

    public ResumeConfig(TaskExecutor taskExecutor, MongoTemplate mongoTemplate) {
        this.taskExecutor = taskExecutor;
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public AnnualResumeService annualService() {
        return new AnnualResumeServiceImpl(taskExecutor, monthlyService());
    }

    @Bean
    public MonthlyResumeService monthlyService() {
        return new MonthlyResumeServiceImpl(
                new RegistryServiceImpl<>(revenueRepository(), new RequestRevenueMapper(), new RegistryResponseMapper<>()),
                new RegistryServiceImpl<>(expenditureRepository(), new RequestExpenditureMapper(), new RegistryResponseMapper<>())
        );
    }

    @Bean
    @Primary
    public RegistryRepository<Revenue> revenueRepository() {
        return new RegistryRepositoryMongo<>(mongoTemplate, Revenue.class);
    }

    @Bean
    public RegistryRepository<Expenditure> expenditureRepository() {
        return new RegistryRepositoryMongo<>(mongoTemplate, Expenditure.class);
    }
}
