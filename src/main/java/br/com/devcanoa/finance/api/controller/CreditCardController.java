package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.domain.response.CreditCardResponse;
import br.com.devcanoa.finance.api.mapper.response.CreditCardResponseMapper;
import br.com.devcanoa.finance.api.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.model.CreditCard;
import br.com.devcanoa.finance.api.service.CreditCardService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    Logger logger = LoggerFactory.getLogger(CreditCardController.class);
    private final CreditCardService creditCardService;
    private final CreditCardResponseMapper responseMapper;

    public CreditCardController(final CreditCardService creditCardService, final CreditCardResponseMapper responseMapper) {
        this.creditCardService = creditCardService;
        this.responseMapper = responseMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CreditCardResponse>> listAll() {
        logger.info("Get");
        return ResponseEntity.ok(creditCardService.findAll().stream()
                .map(c -> responseMapper.apply(Map.entry(c, Collections.emptyList())))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getById(@PathVariable final ObjectId id) {
        logger.info("Get -> id: {}", id);
        return ResponseEntity.ok(creditCardService.findById(id)
                .map(c -> responseMapper.apply(Map.entry(c, Collections.emptyList())))
                .orElseThrow(() -> new RegistryNotFoundException("Registry not found with id: " + id)));
    }

    @PostMapping
    public ResponseEntity<CreditCardResponse> insert(@RequestBody final CreditCard creditCard) {
        logger.info("Post -> request: {}", creditCard);
        return new ResponseEntity<>(responseMapper.apply(Map.entry(creditCardService.save(creditCard), Collections.emptyList())), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreditCardResponse> update(@PathVariable final ObjectId id,
                                                     @RequestBody final CreditCard creditCard) {
        logger.info("Put -> id: {}, request: {}", id, creditCard);
        return ResponseEntity.ok(responseMapper.apply(Map.entry(creditCardService.save(creditCard), Collections.emptyList())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final ObjectId id) {
        logger.info("Delete -> id: {}", id);
        creditCardService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
