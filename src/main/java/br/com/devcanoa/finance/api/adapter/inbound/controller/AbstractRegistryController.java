package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractRegistryController<T extends Registry> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRegistryController.class);

    private final RegistryMapper<T> mapper;
    private final RegistryService<T> service;

    public AbstractRegistryController(final RegistryService<T> service, RegistryMapper<T> mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getById(@PathVariable final String id) {
        LOGGER.info("Get -> id: {}", id);
        return ResponseEntity.ok(mapper.mapToResponse(service.getById(id), FinanceDate.now()));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody final RegistryRequest request) {
        LOGGER.info("Post -> request: {}", request);
        service.insert(mapper.mapToDomain(new ObjectId().toString(), request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final String id, @RequestBody final RegistryRequest request) {
        LOGGER.info("Put -> id: {}, request: {}", id, request);
        service.update(mapper.mapToDomain(id, request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final String id) {
        LOGGER.info("Delete -> id: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listByDescription(@RequestParam(name = "description") final String description) {
        LOGGER.info("Get -> description: {}", description);
        return ResponseEntity.ok(service.getByDescription(description).stream()
                .map(registry -> mapper.mapToResponse(registry, FinanceDate.now()))
                .toList());
    }

    @PostMapping("/date")
    public ResponseEntity<List<RegistryResponse>> getByYearAndMonth(@RequestBody final FinanceDate date) {
        LOGGER.info("Get -> date: {}", date);
        return ResponseEntity.ok(service.getByDate(date).stream()
                .map(registry -> mapper.mapToResponse(registry, date))
                .toList());
    }
}