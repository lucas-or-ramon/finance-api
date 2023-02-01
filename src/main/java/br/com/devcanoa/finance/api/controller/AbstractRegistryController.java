package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.request.RegistryRequest;
import br.com.devcanoa.finance.api.controller.request.RegistryRequestMapper;
import br.com.devcanoa.finance.api.controller.response.RegistryResponse;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.controller.response.ResumeResponse;
import br.com.devcanoa.finance.api.controller.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.exception.FinanceException;
import br.com.devcanoa.finance.api.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.service.FinanceService;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

public class AbstractRegistryController<T extends Registry> {

    private final FinanceService<T> financeService;
    private final RegistryRequestMapper<T> requestMapper;
    private final RegistryResponseMapper<T> responseMapper;

    public AbstractRegistryController(final FinanceService<T> financeService,
                                      final RegistryResponseMapper<T> responseMapper,
                                      final RegistryRequestMapper<T> requestMapper) {
        this.financeService = financeService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getById(@PathVariable final ObjectId id) {
        return ResponseEntity.ok(financeService.getById(id)
                .map(responseMapper)
                .orElseThrow(() -> new RegistryNotFoundException("Registry not found with id: " + id)));
    }

    @PostMapping
    public ResponseEntity<RegistryResponse> insert(@RequestBody final RegistryRequest registryRequest) {
        final var registry = requestMapper.mapper(Pair.of(new ObjectId(), registryRequest));
        return new ResponseEntity<>(financeService.insert(registry)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to save registry")), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> update(@PathVariable final ObjectId id,
                                                   @RequestBody final RegistryRequest registryRequest) {
        final var registry = requestMapper.mapper(Pair.of(id, registryRequest));
        return ResponseEntity.ok(financeService.update(registry)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to update registry")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistryResponse> delete(@PathVariable final ObjectId id) {
        return ResponseEntity.ok(financeService.delete(id)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to delete registry")));
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listByDescription(@RequestParam(name = "description") final String description) {
        return ResponseEntity.ok(financeService.getByDescription(description)
                .stream()
                .map(responseMapper)
                .toList());
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<RegistryResponse>> getByYearAndMonth(@PathVariable final int year,
                                                                    @PathVariable final int month) {
        return ResponseEntity.ok(financeService.getByDate(FinanceDate.getDateFrom(year, month))
                .stream()
                .map(responseMapper)
                .toList());
    }

    @GetMapping(value = "/resume/{year}/{month}")
    public ResponseEntity<ResumeResponse> getRevenuesResume(@PathVariable final int year,
                                                            @PathVariable final int month) {
        return ResponseEntity.ok(
                Stream.of(financeService.getByDate(FinanceDate.getDateFrom(year, month)))
                        .map(new ResumeResponseMapper<>(responseMapper))
                        .toList()
                        .get(0));
    }
}