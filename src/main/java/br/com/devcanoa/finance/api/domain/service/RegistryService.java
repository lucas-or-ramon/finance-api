package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.model.Registry;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public interface RegistryService<T extends Registry> {

    List<T> getByDescription(final String description);
    T getById(final String id);
    List<T> getByDate(final LocalDate date);
    void insert(final T registry);
    void update(final T registry);
    void delete(final String id);
}
