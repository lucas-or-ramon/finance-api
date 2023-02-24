package br.com.devcanoa.finance.api.app.adapter.controller.resume;

import br.com.devcanoa.finance.api.domain.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.domain.port.service.MonthlyResumeService;
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
    private final MonthlyResumeService monthlyService;

    public MonthlyResumeController(final MonthlyResumeService monthlyService) {
        this.monthlyService = monthlyService;
    }

    @GetMapping(value = "/monthly/{year}/{month}")
    public ResponseEntity<MonthlyResponse> getMonthlyResume(@PathVariable final int year,
                                                            @PathVariable final int month) {
        logger.info("Monthly -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(monthlyService.getResume(FinanceDate.getDateFrom(year, month)));
    }
}
