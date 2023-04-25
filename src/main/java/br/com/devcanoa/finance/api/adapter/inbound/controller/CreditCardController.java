package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.adapter.inbound.dto.Response;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);

    private final CreditCardService creditCardService;

    public CreditCardController(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping()
    public ResponseEntity<List<Response.CreditCardDto>> listAll() {
        LOGGER.info("Get");
        return ResponseEntity.ok(creditCardService.listAll().stream()
                        .map(Response.CreditCardDto::mapToResponse)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable final String id) {
        LOGGER.info("Get -> id: {}", id);
        final var result = creditCardService.getById(id);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok(Response.CreditCardDto.mapToResponse(result.getSuccess()));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody @Valid final Request.CreditCardDto request) {
        LOGGER.info("Post -> request: {}", request);
        final var result = creditCardService.insert(request);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final String id, @RequestBody @Valid final Request.CreditCardDto request) {
        LOGGER.info("Put -> id: {}, request: {}", id, request);
        final var result = creditCardService.update(id, request);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final String id) {
        LOGGER.info("Delete -> id: {}", id);
        final var result = creditCardService.delete(id);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getFailure());
        }
        return ResponseEntity.ok().build();
    }
}
