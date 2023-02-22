package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.mapper.registry.ExpenditureMapper;
import br.com.devcanoa.finance.api.mapper.registry.RevenueMapper;
import br.com.devcanoa.finance.api.mapper.request.RequestExpenditureMapper;
import br.com.devcanoa.finance.api.mapper.request.RequestRevenueMapper;
import br.com.devcanoa.finance.api.mapper.response.CreditCardResponseMapper;
import br.com.devcanoa.finance.api.mapper.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.mapper.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import br.com.devcanoa.finance.api.repository.MongoRepositoryImpl;
import br.com.devcanoa.finance.api.service.CreditCardService;
import br.com.devcanoa.finance.api.service.RecurrencyService;
import br.com.devcanoa.finance.api.service.RegistryRecurrencyService;
import br.com.devcanoa.finance.api.service.RegistryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class FinanceConfig {

    private final MongoTemplate mongoTemplate;
    private final CreditCardService creditCardService;
    private final CreditCardResponseMapper creditCardResponseMapper;

    public FinanceConfig(MongoTemplate mongoTemplate, CreditCardService creditCardService, CreditCardResponseMapper creditCardResponseMapper) {
        this.mongoTemplate = mongoTemplate;
        this.creditCardService = creditCardService;
        this.creditCardResponseMapper = creditCardResponseMapper;
    }

    @Bean
    public RegistryService<Revenue> revenueRegistryService() {
        final var mongoRepository = revenueRepository();
        return new RegistryService<>(
                mongoRepository,
                new RecurrencyService<>(
                        mongoRepository,
                        new RegistryRecurrencyService<>(new RevenueMapper())),
                new RegistryResponseMapper<>(),
                new ResumeResponseMapper<>(
                        creditCardResponseMapper,
                        creditCardService,
                        new RegistryResponseMapper<>()),
                new RequestRevenueMapper());
    }

    @Bean
    @Primary
    public MongoRepository<Revenue> revenueRepository() {
        return new MongoRepositoryImpl<>(mongoTemplate, Revenue.class);
    }

    @Bean
    public RegistryService<Expenditure> expenditureRegistryService() {
        final var mongoRepository = expenditureRepository();
        return new RegistryService<>(
                mongoRepository,
                new RecurrencyService<>(
                        mongoRepository,
                        new RegistryRecurrencyService<>(new ExpenditureMapper())),
                new RegistryResponseMapper<>(),
                new ResumeResponseMapper<>(
                        creditCardResponseMapper,
                        creditCardService,
                        new RegistryResponseMapper<>()),
                new RequestExpenditureMapper());
    }

    @Bean
    public MongoRepository<Expenditure> expenditureRepository() {
        return new MongoRepositoryImpl<>(mongoTemplate, Expenditure.class);
    }
}
