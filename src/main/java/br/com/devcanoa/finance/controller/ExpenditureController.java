package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.controller.request.RegistryRequest;
import br.com.devcanoa.finance.controller.response.RegistryResponse;
import br.com.devcanoa.finance.domain.FinanceDate;
import br.com.devcanoa.finance.model.Expenditure;
import br.com.devcanoa.finance.service.ExpenditureService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expenditure")
public class ExpenditureController {

    Logger logger = LoggerFactory.getLogger(ExpenditureController.class);

    public final ExpenditureService expenditureService;

    @Autowired
    public ExpenditureController(ExpenditureService expenditureService) {
        this.expenditureService = expenditureService;
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listExpenditure(@RequestParam(required = false, name = "description") final String description) {
        logger.info("Expenditure: {description: {}}", description);
        return new ResponseEntity<>(RegistryResponse.fromExpenditureList(expenditureService.getAllExpenditures(description)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getExpenditureById(@PathVariable final ObjectId id) {
        logger.info("Expenditure: {id: {}}", id);
        return new ResponseEntity<>(RegistryResponse.fromExpenditure(expenditureService.getExpenditureById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{year}/{month}")
    public ResponseEntity<List<RegistryResponse>> getExpendituresByYearAndMonth(@PathVariable final int year,
                                                                                @PathVariable final int month) {
        logger.info("Expenditure: {year: {}, month: {}}", year, month);
        return ResponseEntity.ok(RegistryResponse.fromExpenditureList(
                expenditureService.getExpendituresByYearAndMonth(FinanceDate.getDateFrom(year, month))));
    }

    @PostMapping
    public ResponseEntity<RegistryResponse> insertExpenditure(@RequestBody final RegistryRequest registryRequest) {
        logger.info("Expenditure: {{}}", registryRequest);
        Expenditure expenditure = Expenditure.from(registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromExpenditure(expenditureService.insertExpenditure(expenditure)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> updateExpenditure(@PathVariable final ObjectId id, @RequestBody final RegistryRequest registryRequest) {
        logger.info("Expenditure: {id: {}, {}}", id, registryRequest);
        Expenditure expenditure = Expenditure.withId(id, registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromExpenditure(expenditureService.updateExpenditure(expenditure)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistryResponse> deleteExpenditure(@PathVariable final ObjectId id) {
        logger.info("Expenditure: {id: {}}", id);
        return new ResponseEntity<>(RegistryResponse.fromExpenditure(expenditureService.deleteExpenditure(id)), HttpStatus.NO_CONTENT);
    }
}
