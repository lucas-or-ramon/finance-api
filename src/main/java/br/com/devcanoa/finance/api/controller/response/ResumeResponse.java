package br.com.devcanoa.finance.api.controller.response;

import java.util.List;

public record ResumeResponse(double total, List<RegistryResponse> registries, List<CreditCardResponse> creditCards) {
}
