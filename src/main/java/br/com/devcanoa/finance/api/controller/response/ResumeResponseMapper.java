package br.com.devcanoa.finance.api.controller.response;

import br.com.devcanoa.finance.api.model.Registry;

import java.util.List;
import java.util.function.Function;

public class ResumeResponseMapper<T extends Registry> implements Function<List<T>, ResumeResponse> {

    private final RegistryResponseMapper<T> responseMapper;

    public ResumeResponseMapper(final RegistryResponseMapper<T> responseMapper) {
        this.responseMapper = responseMapper;
    }

    @Override
    public ResumeResponse apply(final List<T> registries) {
        return new ResumeResponse(
                registries.stream().map(responseMapper).toList(),
                registries.stream().mapToDouble(Registry::getValue).sum()
        );
    }
}
