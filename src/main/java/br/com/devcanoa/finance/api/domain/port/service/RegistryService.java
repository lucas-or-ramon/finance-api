package br.com.devcanoa.finance.api.domain.port.service;

import br.com.devcanoa.finance.api.domain.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.dto.response.RegistryResponse;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public interface RegistryService {

    List<RegistryResponse> getByDescription(final String description);
    RegistryResponse getById(final ObjectId id);
    List<RegistryResponse> getByDate(final LocalDate date);
    void insert(final RegistryRequest request);
    void update(final ObjectId id, final RegistryRequest request);
    void delete(final ObjectId id);
}
