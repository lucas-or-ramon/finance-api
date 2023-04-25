package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.adapter.inbound.dto.Response;

public interface AnnualResumeService {

    Response.AnnualDto annualResume(final Request.FinanceDateDto date);
}
