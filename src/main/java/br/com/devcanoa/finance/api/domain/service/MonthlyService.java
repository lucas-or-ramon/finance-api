package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.Monthly;

public interface MonthlyService {

    Monthly getMonthlyResume(final Request.FinanceDateDto date);
}
