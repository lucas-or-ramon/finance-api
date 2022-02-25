package br.com.devcanoa.finance.model;

import br.com.devcanoa.finance.controller.request.RegistryRequest;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Objects;

public class Registry {
    @Id
    private final ObjectId id;
    private final LocalDate date;
    private final Double value;
    private final String description;

    protected Registry(ObjectId id, LocalDate date, Double value, String description) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
    }

    protected Registry(RegistryRequest registryRequest) {
        this.id = new ObjectId();
        this.date = registryRequest.getDate();
        this.value = registryRequest.getValue();
        this.description = registryRequest.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry registry = (Registry) o;
        return date.equals(registry.date) && description.equals(registry.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description);
    }

    @Override
    public String toString() {
        return "id: " + id + ", date: " + date + ", value: " + value + ", description: " + description;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
}
