package br.com.devcanoa.finance.api.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record RegistryResponse(String id, LocalDate date, Double value, String description) {
}
