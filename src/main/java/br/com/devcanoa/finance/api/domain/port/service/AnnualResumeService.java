package br.com.devcanoa.finance.api.domain.port.service;

import br.com.devcanoa.finance.api.domain.dto.response.AnnualResponse;

import java.time.LocalDate;

public interface AnnualResumeService {

    AnnualResponse getResume(LocalDate date);
}
