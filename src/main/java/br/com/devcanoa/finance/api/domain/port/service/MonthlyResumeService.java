package br.com.devcanoa.finance.api.domain.port.service;

import br.com.devcanoa.finance.api.domain.dto.response.MonthlyResponse;

import java.time.LocalDate;

public interface MonthlyResumeService {

    MonthlyResponse getResume(LocalDate date);
}
