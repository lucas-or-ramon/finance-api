package br.com.devcanoa.finance.api.infra.config;

import br.com.devcanoa.finance.api.domain.adapter.service.CreditCardServiceImpl;
import br.com.devcanoa.finance.api.domain.adapter.mapper.request.CreditCardRequestMapper;
import br.com.devcanoa.finance.api.domain.adapter.mapper.response.CreditCardResponseMapper;
import br.com.devcanoa.finance.api.domain.port.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.port.service.CreditCardService;
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
        return new CreditCardServiceImpl(creditCardRepository, new CreditCardRequestMapper(), new CreditCardResponseMapper());
    }
}
