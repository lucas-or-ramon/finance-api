package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.controller.response.AnnualResumeResponse;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.service.AnnualResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class AnnualResumeController {

    Logger logger = LoggerFactory.getLogger(AnnualResumeController.class);
    private final AnnualResumeService annualService;

    public AnnualResumeController(final AnnualResumeService annualService) {
        this.annualService = annualService;
    }

    @GetMapping(value = "/annual/{year}/{month}")
    public ResponseEntity<AnnualResumeResponse> getAnnualResume(@PathVariable final int year,
                                                                @PathVariable final int month) {
        logger.info("Annual -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(annualService.getAnnualResume(FinanceDate.getDateFrom(year, month)));
    }
}
