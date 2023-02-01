package br.com.devcanoa.finance.api.controller.response;

import java.util.List;

public record ResumeResponse(List<RegistryResponse> registries, double total) {
}
