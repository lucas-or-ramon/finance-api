package br.com.devcanoa.finance.api.app.adapter.controller.resume;

import br.com.devcanoa.finance.api.domain.dto.response.AnnualResponse;
import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.domain.adapter.service.AnnualResumeServiceImpl;
import br.com.devcanoa.finance.api.domain.port.service.AnnualResumeService;
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
    private final AnnualResumeService service;

    public AnnualResumeController(final AnnualResumeService service) {
        this.service = service;
    }

    @GetMapping(value = "/annual/{year}/{month}")
    public ResponseEntity<AnnualResponse> getAnnualResume(@PathVariable final int year,
                                                          @PathVariable final int month) {
        logger.info("Annual -> year: {}, month: {}", year, month);
        return ResponseEntity.ok(service.getResume(FinanceDate.getDateFrom(year, month)));
    }
}
