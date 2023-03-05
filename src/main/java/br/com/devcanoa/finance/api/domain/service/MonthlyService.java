package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.Monthly;

import java.time.LocalDate;

public interface MonthlyService {

    Monthly getMonthlyResume(LocalDate date);
}
