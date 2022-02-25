package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.controller.request.RegistryRequest;
import br.com.devcanoa.finance.controller.response.RegistryResponse;
import br.com.devcanoa.finance.domain.FinanceDate;
import br.com.devcanoa.finance.model.Revenue;
import br.com.devcanoa.finance.service.RevenueService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/revenue")
public class RevenueController {

    public final RevenueService revenueService;

    @Autowired
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public ResponseEntity<List<RegistryResponse>> listRevenues(@RequestParam(required = false, name = "description") final String description) {
        return new ResponseEntity<>(RegistryResponse.fromRevenueList(revenueService.getAllRevenues(description)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> getRevenueById(@PathVariable final ObjectId id) {
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.getRevenueById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{year}/{month}")
    public ResponseEntity<List<RegistryResponse>> getRevenuesByYearAndMonth(@PathVariable final int year,
                                                                            @PathVariable final int month) {
        return ResponseEntity.ok(RegistryResponse.fromRevenueList(
                revenueService.getRevenuesByYearAndMonth(FinanceDate.getDateFrom(year, month))));
    }

    @PostMapping
    public ResponseEntity<RegistryResponse> insertRevenues(@RequestBody final RegistryRequest registryRequest) {
        Revenue revenue = Revenue.from(registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.insertRevenue(revenue)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistryResponse> updateRevenue(@PathVariable final ObjectId id, @RequestBody final RegistryRequest registryRequest) {
        Revenue revenue = Revenue.withId(id, registryRequest);
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.updateRevenue(revenue)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistryResponse> deleteRevenue(@PathVariable final ObjectId id) {
        return new ResponseEntity<>(RegistryResponse.fromRevenue(revenueService.deleteRevenue(id)), HttpStatus.NO_CONTENT);
    }
}
