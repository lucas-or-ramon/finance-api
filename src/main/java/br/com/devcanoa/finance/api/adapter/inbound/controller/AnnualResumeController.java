package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.FinanceDateRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.AnnualResponse;
import br.com.devcanoa.finance.api.domain.service.AnnualResumeService;
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
public class AnnualResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnualResumeController.class);

    private final AnnualResumeService service;

    public AnnualResumeController(final AnnualResumeService service) {
        this.service = service;
    }

    @PostMapping(value = "/annual")
    public ResponseEntity<AnnualResponse> getAnnualResume(@RequestBody @Valid final FinanceDateRequest date) {
        LOGGER.info("Annual -> date: {}", date);
        return ResponseEntity.ok(service.annualResume(date.mapToDomain()));
    }
}
