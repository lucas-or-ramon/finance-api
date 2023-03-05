package br.com.devcanoa.finance.api.config;

import br.com.devcanoa.finance.api.domain.service.impl.CreditCardServiceImpl;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditCardConfig {

    private final CreditCardRepository creditCardRepository;

    public CreditCardConfig(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Bean
    public CreditCardService creditCardService() {
        return new CreditCardServiceImpl(creditCardRepository);
    }
}
