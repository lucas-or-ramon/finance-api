package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.request.RegistryRequest;
import br.com.devcanoa.finance.api.controller.request.RegistryRequestMapper;
import br.com.devcanoa.finance.api.controller.response.RegistryResponse;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.controller.response.ResumeResponse;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.exception.FinanceException;
import br.com.devcanoa.finance.api.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import br.com.devcanoa.finance.api.service.RegistryService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractRegistryController<T extends Registry> {

    Logger logger = LoggerFactory.getLogger(AbstractRegistryController.class);
    private final MongoRepository<T> mongoRepository;
    private final RegistryRequestMapper<T> requestMapper;
    private final RegistryResponseMapper<T> responseMapper;
    private final RegistryService<T> registryService;

    public AbstractRegistryController(final MongoRepository<T> mongoRepository,
                                      final RegistryResponseMapper<T> responseMapper,
                                      final RegistryRequestMapper<T> requestMapper,
                                      final RegistryService<T> registryService) {
        this.mongoRepository = mongoRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.registryService = registryService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getById(@PathVariable final ObjectId id) {
        logger.info("Get -> id: {}", id);
        return ResponseEntity.ok(mongoRepository.getById(id)
                .map(responseMapper)
                .orElseThrow(() -> new RegistryNotFoundException("Registry not found with id: " + id)));
    }

    @PostMapping
    public ResponseEntity<RegistryResponse> insert(@RequestBody final RegistryRequest registryRequest) {
        logger.info("Post -> request: {}", registryRequest);
        final var registry = requestMapper.mapper(Pair.of(new ObjectId(), registryRequest));
        return new ResponseEntity<>(mongoRepository.insert(registry)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to save registry")), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> update(@PathVariable final ObjectId id,
                                                   @RequestBody final RegistryRequest registryRequest) {
        logger.info("Put -> id: {}, request: {}", id, registryRequest);
        final var registry = requestMapper.mapper(Pair.of(id, registryRequest));
        return ResponseEntity.ok(mongoRepository.update(registry)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to update registry")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistryResponse> delete(@PathVariable final ObjectId id) {
        logger.info("Delete -> id: {}", id);
        return ResponseEntity.ok(mongoRepository.delete(id)
                .map(responseMapper)
                .orElseThrow(() -> new FinanceException("Error when trying to delete registry")));
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listByDescription(@RequestParam(name = "description") final String description) {
        logger.info("Get -> description: {}", description);
        return ResponseEntity.ok(mongoRepository.getByDescription(description)
                .stream()
                .map(responseMapper)
                .toList());
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<ResumeResponse> getByYearAndMonth(@PathVariable final int year,
                                                            @PathVariable final int month) {
        logger.info("Get -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(registryService.getResume(FinanceDate.getDateFrom(year, month)));
    }
}