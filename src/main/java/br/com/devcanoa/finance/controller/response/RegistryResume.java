package br.com.devcanoa.finance.controller.response;

import java.util.List;

public class RegistryResume {
    private List<RegistryResponse> registries;
    private double total;

    public RegistryResume(List<RegistryResponse> registries) {
        this.registries = registries;
        this.total = this.registries.stream().mapToDouble(RegistryResponse::getValue).sum();
    }

    public List<RegistryResponse> getRegistries() {
        return registries;
    }

    public double getTotal() {
        return total;
    }
}
