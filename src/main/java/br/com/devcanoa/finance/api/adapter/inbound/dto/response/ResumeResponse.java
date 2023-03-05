package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import java.util.List;

public record ResumeResponse(double total, List<RegistryResponse> registries, List<CreditCardResponse> creditCards) {
}
