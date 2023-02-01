package br.com.devcanoa.finance.api.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Registry {

    @Id
    private final ObjectId id;
    private final Double value;
    private final LocalDate date;
    private final String description;

    public Registry(final ObjectId id, final LocalDate date, final Double value, final String description) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
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
