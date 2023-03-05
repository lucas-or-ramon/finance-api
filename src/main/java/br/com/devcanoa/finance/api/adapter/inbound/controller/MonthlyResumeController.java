package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.MonthlyMapper;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Monthly;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class MonthlyResumeController {

    Logger logger = LoggerFactory.getLogger(MonthlyResumeController.class);
    private final MonthlyMapper mapper;
    private final MonthlyService service;

    public MonthlyResumeController(final MonthlyService service, final MonthlyMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "/monthly/{year}/{month}")
    public ResponseEntity<MonthlyResponse> getMonthlyResume(@PathVariable final int year,
                                                            @PathVariable final int month) {
        logger.info("Monthly -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(mapper.mapToResponse(service.getMonthlyResume(FinanceDate.getDateFrom(year, month))));
    }
}
