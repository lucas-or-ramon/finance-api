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
@RequestMapping(value = "/resumo")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(value = "/mensal/{ano}/{mes}")
    public ResponseEntity<MonthlyResume> getMonthlyResume(@PathVariable(name = "ano") final int year,
                                                          @PathVariable(name = "mes") final int month) {
        return ResponseEntity.ok(resumeService.getMonthlyResume(FinanceDate.getDateFrom(year, month)));
    }

    @GetMapping(value = "/anual/{ano}/{mes}")
    public ResponseEntity<AnnualResume> getAnnualResume(@PathVariable(name = "ano") final int year,
                                                        @PathVariable(name = "mes") final int month) {
        return ResponseEntity.ok(resumeService.getAnnualResume(FinanceDate.getDateFrom(year, month)));
    }
}
