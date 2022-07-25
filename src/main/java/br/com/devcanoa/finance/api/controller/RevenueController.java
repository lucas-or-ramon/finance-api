package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.response.RegistryResponse;
import br.com.devcanoa.finance.api.controller.response.RegistryResume;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.service.RevenueService;
import br.com.devcanoa.finance.api.controller.request.RegistryRequest;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/revenue")
public class RevenueController {

    Logger logger = LoggerFactory.getLogger(RevenueController.class);

    public final RevenueService revenueService;

    @Autowired
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listRevenues(@RequestParam(required = false, name = "description") final String description) {
        logger.info("Revenue: {description: {}}", description);
        return new ResponseEntity<>(RegistryResponse.fromRevenueList(revenueService.getAllRevenues(description)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getRevenueById(@PathVariable final ObjectId id) {
        logger.info("Revenue: {id: {}}", id);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.getRevenueById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{year}/{month}")
    public ResponseEntity<List<RegistryResponse>> getRevenuesByYearAndMonth(@PathVariable final int year,
                                                                            @PathVariable final int month) {
        logger.info("Revenue: {year: {}, month: {}}", year, month);
        return ResponseEntity.ok(RegistryResponse.fromRevenueList(
                revenueService.getRevenuesByYearAndMonth(FinanceDate.getDateFrom(year, month))));
    }

    @GetMapping(value = "/resume/{year}/{month}")
    public ResponseEntity<RegistryResume> getRevenuesResume(@PathVariable final int year,
                                                            @PathVariable final int month) {
        logger.info("Revenue: {year: {}, month: {}}", year, month);
        return ResponseEntity.ok(new RegistryResume(RegistryResponse.fromRevenueList(
                revenueService.getRevenuesByYearAndMonth(FinanceDate.getDateFrom(year, month)))));
    }

    @PostMapping
    public ResponseEntity<RegistryResponse> insertRevenues(@RequestBody final RegistryRequest registryRequest) {
        logger.info("Revenue: {{}}", registryRequest);
        Revenue revenue = Revenue.from(registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.insertRevenue(revenue)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> updateRevenue(@PathVariable final ObjectId id, @RequestBody final RegistryRequest registryRequest) {
        logger.info("Revenue: {id: {}, {}}", id, registryRequest);
        Revenue revenue = Revenue.withId(id, registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.updateRevenue(revenue)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistryResponse> deleteRevenue(@PathVariable final ObjectId id) {
        logger.info("Revenue: {id: {}}", id);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.deleteRevenue(id)), HttpStatus.NO_CONTENT);
    }
}
