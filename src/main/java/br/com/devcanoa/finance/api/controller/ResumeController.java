package br.com.devcanoa.finance.api.controller;

import br.com.devcanoa.finance.api.domain.AnnualResume;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.domain.MonthlyResume;
import br.com.devcanoa.finance.api.service.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class ResumeController {

    Logger logger = LoggerFactory.getLogger(ResumeController.class);

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(value = "/monthly/{year}/{month}")
    public ResponseEntity<MonthlyResume> getMonthlyResume(@PathVariable final int year,
                                                          @PathVariable final int month) {
        logger.info("Resume: {year: {}, month: {}}", year, month);
        return ResponseEntity.ok(resumeService.getMonthlyResume(FinanceDate.getDateFrom(year, month)));
    }

    @GetMapping(value = "/annual/{year}/{month}")
    public ResponseEntity<AnnualResume> getAnnualResume(@PathVariable final int year,
                                                        @PathVariable final int month) {
        logger.info("Resume: {year: {}, month: {}}", year, month);
        return ResponseEntity.ok(resumeService.getAnnualResume(FinanceDate.getDateFrom(year, month)));
    }
}
