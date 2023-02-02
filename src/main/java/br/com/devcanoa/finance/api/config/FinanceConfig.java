package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import br.com.devcanoa.finance.api.repository.MongoRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class FinanceConfig {

    private final MongoTemplate mongoTemplate;

    public FinanceConfig(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public MongoRepository<Revenue> revenueRepository() {
        return new MongoRepositoryImpl<>(mongoTemplate, Revenue.class);
    }

    @Bean
    public MongoRepository<Expenditure> expenditureRepository() {
        return new MongoRepositoryImpl<>(mongoTemplate, Expenditure.class);
    }
}
