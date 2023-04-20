package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Monthly;

public interface MonthlyService {

    Monthly getMonthlyResume(final FinanceDate date);
}
