package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.adapter.outbound.entity.ExpenditureEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.adapter.outbound.repository.CategoryRepositoryMongo;
import br.com.devcanoa.finance.api.adapter.outbound.repository.RegistryRepositoryMongo;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.CategoryService;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import br.com.devcanoa.finance.api.domain.service.impl.CategoryServiceImpl;
import br.com.devcanoa.finance.api.domain.service.impl.RegistryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class RegistryConfig {

    private final MongoTemplate mongoTemplate;

    public RegistryConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    @Primary
    public RegistryRepository revenueRepository() {
        return new RegistryRepositoryMongo(mongoTemplate, RevenueEntity.class, new RegistryEntityMapper.Revenue());
    }

    @Bean
    public RegistryRepository expenditureRepository() {
        return new RegistryRepositoryMongo(mongoTemplate, ExpenditureEntity.class, new RegistryEntityMapper.Expenditure());
    }

    @Bean
    public RegistryService revenueService(final CreditCardService creditCardService, final CategoryService categoryService) {
        return new RegistryServiceImpl(revenueRepository(), creditCardService, categoryService);
    }

    @Bean
    public RegistryService expenditureService(final CreditCardService creditCardService, final CategoryService categoryService) {
        return new RegistryServiceImpl(expenditureRepository(), creditCardService, categoryService);
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(new CategoryRepositoryMongo(mongoTemplate));
    }
}
