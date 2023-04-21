package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.FinanceDateRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.MonthlyMapper;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class MonthlyResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonthlyResumeController.class);

    private final MonthlyMapper mapper;
    private final MonthlyService service;

    public MonthlyResumeController(final MonthlyService service, final MonthlyMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping(value = "/monthly")
    public ResponseEntity<MonthlyResponse> getMonthlyResume(@RequestBody @Valid final FinanceDateRequest date) {
        LOGGER.info("Monthly -> date: {}", date);
        return ResponseEntity.ok(mapper.mapToResponse(service.getMonthlyResume(date.mapToDomain())));
    }
}
