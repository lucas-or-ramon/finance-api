package br.com.devcanoa.finance.api.app.adapter.controller.creditcard;

import br.com.devcanoa.finance.api.domain.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.domain.dto.response.CreditCardResponse;
import br.com.devcanoa.finance.api.domain.port.service.CreditCardService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    Logger logger = LoggerFactory.getLogger(CreditCardController.class);
    private final CreditCardService creditCardService;

    public CreditCardController(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping()
    public ResponseEntity<List<CreditCardResponse>> listAll() {
        logger.info("Get");
        return ResponseEntity.ok(creditCardService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getById(@PathVariable final ObjectId id) {
        logger.info("Get -> id: {}", id);
        return ResponseEntity.ok(creditCardService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody final CreditCardRequest request) {
        logger.info("Post -> request: {}", request);
        creditCardService.insert(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable final ObjectId id, @RequestBody final CreditCardRequest request) {
        logger.info("Put -> id: {}, request: {}", id, request);
        creditCardService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final ObjectId id) {
        logger.info("Delete -> id: {}", id);
        creditCardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
