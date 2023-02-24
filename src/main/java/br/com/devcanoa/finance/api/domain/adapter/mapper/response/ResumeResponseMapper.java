package br.com.devcanoa.finance.api.domain.adapter.mapper.response;

import br.com.devcanoa.finance.api.domain.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.dto.response.ResumeResponse;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import br.com.devcanoa.finance.api.domain.port.repository.CreditCardRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;

@Service
public class ResumeResponseMapper<T extends Registry> implements Function<List<T>, ResumeResponse> {

    private final CreditCardResponseMapper creditCardResponseMapper;
    private final CreditCardRepository creditCardRepository;
    private final RegistryResponseMapper<T> registryResponseMapper;

    public ResumeResponseMapper(CreditCardResponseMapper creditCardResponseMapper,
                                CreditCardRepository creditCardRepository,
                                RegistryResponseMapper<T> registryResponseMapper) {
        this.creditCardResponseMapper = creditCardResponseMapper;
        this.creditCardRepository = creditCardRepository;
        this.registryResponseMapper = registryResponseMapper;
    }

    @Override
    public ResumeResponse apply(final List<T> registries) {
        final var total = registries.stream().mapToDouble(Registry::getValue).sum();
        final var registriesResponse = getRegistriesResponse(registries);
//        final var creditCards = getCreditCards(registries);
        return new ResumeResponse(total, registriesResponse, null);
    }

    private List<RegistryResponse> getRegistriesResponse(final List<T> registries) {
        return registries.stream()
                .filter(r -> isNull(r.getCreditCardId()))
                .map(registryResponseMapper)
                .toList();
    }

//    private List<CreditCardResponse> getCreditCards(final List<T> registries) {
//        final var creditCardsResponse = new ArrayList<CreditCardResponse>();
//        getCreditCardRegistriesById(registries).forEach((id, creditCardRegistries) -> creditCardRepository.findById(id)
//                .ifPresent(creditCard -> creditCardsResponse.add(creditCardResponseMapper
//                        .apply(Map.entry(creditCard, creditCardRegistries.stream().map(registryResponseMapper).toList())))));
//        return creditCardsResponse;
//    }

    private Map<ObjectId, List<T>> getCreditCardRegistriesById(List<T> registries) {
        return registries.stream()
                .filter(r -> nonNull(r.getCreditCardId()))
                .collect(groupingBy(Registry::getCreditCardId));
    }
}
