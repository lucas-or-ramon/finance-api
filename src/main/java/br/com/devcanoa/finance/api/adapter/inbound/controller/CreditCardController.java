package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.CreditCardMapper;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.devcanoa.finance.api.adapter.inbound.mapper.CreditCardMapper.mapToDomain;
import static br.com.devcanoa.finance.api.adapter.inbound.mapper.CreditCardMapper.mapToResponse;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);

    private final CreditCardService creditCardService;

    public CreditCardController(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping()
    public ResponseEntity<List<CreditCardResponse>> listAll() {
        LOGGER.info("Get");
        return ResponseEntity.ok(creditCardService.listAll().stream().map(CreditCardMapper::mapToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getById(@PathVariable final String id) {
        LOGGER.info("Get -> id: {}", id);
        return ResponseEntity.ok(mapToResponse(creditCardService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody final CreditCardRequest request) {
        LOGGER.info("Post -> request: {}", request);
        creditCardService.insert(mapToDomain(new ObjectId().toString(), request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final String id, @RequestBody final CreditCardRequest request) {
        LOGGER.info("Put -> id: {}, request: {}", id, request);
        creditCardService.update(mapToDomain(id, request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final String id) {
        LOGGER.info("Delete -> id: {}", id);
        creditCardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
