package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.response.AnnualResponse;

import java.time.LocalDate;

public interface AnnualResumeService {

    AnnualResponse annualResume(LocalDate date);
}
