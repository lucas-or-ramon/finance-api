package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.adapter.inbound.mapper.ExpenditureMapper;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RevenueMapper;
import br.com.devcanoa.finance.api.adapter.outbound.entity.ExpenditureEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.adapter.outbound.repository.RegistryRepositoryMongo;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import br.com.devcanoa.finance.api.domain.model.Revenue;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
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
    public RegistryRepository<RevenueEntity> revenueRepository() {
        return new RegistryRepositoryMongo<>(mongoTemplate, RevenueEntity.class);
    }

    @Bean
    public RegistryRepository<ExpenditureEntity> expenditureRepository() {
        return new RegistryRepositoryMongo<>(mongoTemplate, ExpenditureEntity.class);
    }

    @Bean
    public RegistryService<Revenue> revenueService() {
        return new RegistryServiceImpl<>(revenueRepository(), new RevenueEntityMapper(), creditCardService);
    }

    @Bean
    public RegistryService<Expenditure> expenditureService() {
        return new RegistryServiceImpl<>(expenditureRepository(), new ExpenditureEntityMapper(), creditCardService);
    }

    @Bean
    public RegistryMapper<Revenue> revenueMapper() {
        return new RevenueMapper();
    }

    @Bean
    public RegistryMapper<Expenditure> expenditureMapper() {
        return new ExpenditureMapper();
    }
}
