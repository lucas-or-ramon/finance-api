package br.com.devcanoa.finance.api.app.adapter.controller.registry;

import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.domain.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.port.service.RegistryService;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractRegistryController<T extends Registry> {

    private final Logger logger = LoggerFactory.getLogger(AbstractRegistryController.class);
    private final RegistryService service;

    public AbstractRegistryController(final RegistryService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getById(@PathVariable final ObjectId id) {
        logger.info("Get -> id: {}", id);
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody final RegistryRequest request) {
        logger.info("Post -> request: {}", request);
        service.insert(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final ObjectId id, @RequestBody final RegistryRequest request) {
        logger.info("Put -> id: {}, request: {}", id, request);
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final ObjectId id) {
        logger.info("Delete -> id: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listByDescription(@RequestParam(name = "description") final String description) {
        logger.info("Get -> description: {}", description);
        return ResponseEntity.ok(service.getByDescription(description));
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<RegistryResponse>> getByYearAndMonth(@PathVariable final int year,
                                                            @PathVariable final int month) {
        logger.info("Get -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(service.getByDate(FinanceDate.getDateFrom(year, month)));
    }
}