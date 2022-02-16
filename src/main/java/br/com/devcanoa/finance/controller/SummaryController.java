package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.model.AnnualSummary;
import br.com.devcanoa.finance.model.MonthlySummary;
import br.com.devcanoa.finance.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/resumo")
public class SummaryController {

    private final SummaryService summaryService;

    @Autowired
    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping(value = "/{ano}/{mes}")
    public ResponseEntity<MonthlySummary> getMonthlySummary(@PathVariable(name = "ano") final int year,
                                                            @PathVariable(name = "mes") final int month) {
        LocalDate date = LocalDate.of(year, month, 1);

        return ResponseEntity.ok(summaryService.getMonthlySummary(date));
    }

    @GetMapping(value = "/lastyear/{ano}/{mes}")
    public ResponseEntity<AnnualSummary> getAnnualSummary(@PathVariable(name = "ano") final int year,
                                                          @PathVariable(name = "mes") final int month) {
        LocalDate dateFrom = LocalDate.of(year, month, 1);
        return ResponseEntity.ok(summaryService.getAnnualSummary(dateFrom));
    }
}
