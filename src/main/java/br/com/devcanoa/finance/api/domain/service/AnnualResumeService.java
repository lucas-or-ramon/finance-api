package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.response.AnnualResponse;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public interface AnnualResumeService {

    AnnualResponse annualResume(final FinanceDate date);
}
