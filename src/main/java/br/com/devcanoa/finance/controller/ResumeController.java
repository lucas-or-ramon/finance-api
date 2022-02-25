package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.domain.AnnualResume;
import br.com.devcanoa.finance.domain.FinanceDate;
import br.com.devcanoa.finance.domain.MonthlyResume;
import br.com.devcanoa.finance.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(value = "/monthly/{year}/{month}")
    public ResponseEntity<MonthlyResume> getMonthlyResume(@PathVariable final int year,
                                                          @PathVariable final int month) {
        return ResponseEntity.ok(resumeService.getMonthlyResume(FinanceDate.getDateFrom(year, month)));
    }

    @GetMapping(value = "/annual/{year}/{month}")
    public ResponseEntity<AnnualResume> getAnnualResume(@PathVariable final int year,
                                                        @PathVariable final int month) {
        return ResponseEntity.ok(resumeService.getAnnualResume(FinanceDate.getDateFrom(year, month)));
    }
}
