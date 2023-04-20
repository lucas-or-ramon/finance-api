package br.com.devcanoa.finance.api.adapter.inbound.controller;

import br.com.devcanoa.finance.api.adapter.inbound.dto.response.AnnualResponse;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.service.AnnualResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class AnnualResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnualResumeController.class);

    private final AnnualResumeService service;

    public AnnualResumeController(final AnnualResumeService service) {
        this.service = service;
    }

    @PostMapping(value = "/annual")
    public ResponseEntity<AnnualResponse> getAnnualResume(@RequestBody final FinanceDate date) {
        LOGGER.info("Annual -> date: {}", date);
        return ResponseEntity.ok(service.annualResume(date));
    }
}
