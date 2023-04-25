package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.adapter.inbound.dto.Response;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.devcanoa.finance.api.adapter.inbound.dto.Response.MonthlyDto.mapToResponse;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class MonthlyResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonthlyResumeController.class);

    private final MonthlyService service;

    public MonthlyResumeController(final MonthlyService service) {
        this.service = service;
    }

    @PostMapping(value = "/monthly")
    public ResponseEntity<Response.MonthlyDto> getMonthlyResume(@RequestBody @Valid final Request.FinanceDateDto date) {
        LOGGER.info("Monthly -> date: {}", date);
        return ResponseEntity.ok(mapToResponse(service.getMonthlyResume(date)));
    }
}
