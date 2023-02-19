package br.com.devcanoa.finance.api.controller.response;

import br.com.devcanoa.finance.api.model.Registry;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;

@Service
public class RegistryResponseMapper<T extends Registry> implements Function<T, RegistryResponse> {

    @Override
    public RegistryResponse apply(final T registry) {
        final var creditCardId = registry.getCreditCardId();
        return new RegistryResponse(
                registry.getId().toString(),
                registry.getDate(),
                registry.getValue(),
                registry.getDescription(),
                Objects.nonNull(creditCardId) ? creditCardId.toString() : null
        );
    }
}
