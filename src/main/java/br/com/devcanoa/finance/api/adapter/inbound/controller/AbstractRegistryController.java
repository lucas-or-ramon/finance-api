package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.adapter.inbound.dto.Response;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.devcanoa.finance.api.adapter.inbound.dto.Response.RegistryDto.mapToResponse;

public class AbstractRegistryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRegistryController.class);

    private final RegistryService service;

    public AbstractRegistryController(final RegistryService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable final String id) {
        LOGGER.info("Get -> id: {}", id);
        final var result = service.getById(id);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok(mapToResponse(result.getSuccess()));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody @Valid final Request.RegistryDto request) {
        LOGGER.info("Post -> request: {}", request);
        final var result = service.insert(request);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final String id,
                                         @RequestBody @Valid final Request.RegistryDto request) {
        LOGGER.info("Put -> id: {}, request: {}", id, request);
        final var result = service.update(id, request);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final String id) {
        LOGGER.info("Delete -> id: {}", id);
        final var result = service.delete(id);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Response.RegistryDto>> listByDescription(@RequestParam(name = "description") final String description) {
        LOGGER.info("Get -> description: {}", description);
        return ResponseEntity.ok(service.getByDescription(description).stream()
                .map(Response.RegistryDto::mapToResponse)
                .toList());
    }

    @PostMapping("/date")
    public ResponseEntity<List<Response.RegistryDto>> getByYearAndMonth(@RequestBody @Valid final Request.FinanceDateDto date) {
        LOGGER.info("Get -> date: {}", date);
        return ResponseEntity.ok(service.getByDate(date).stream()
                .map(Response.RegistryDto::mapToResponse)
                .toList());
    }
}