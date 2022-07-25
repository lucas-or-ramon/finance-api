package br.com.devcanoa.finance.api.model;

import br.com.devcanoa.finance.api.controller.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.Category;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "expenditure")
public class Expenditure extends Registry {
    private final Category category;

    @PersistenceConstructor
    private Expenditure(ObjectId id, LocalDate date, Double value, String description, Category category) {
        super(id, date, value, description);
        this.category = category;
    }

    private Expenditure(final RegistryRequest registryRequest) {
        super(registryRequest);
        this.category = registryRequest.getCategory();
    }

    public static Expenditure from(final RegistryRequest registryRequest) {
        return new Expenditure(registryRequest);
    }

    public static Expenditure withId(final ObjectId id, final RegistryRequest registryRequest) {
        return new Expenditure(id, registryRequest.getDate(), registryRequest.getValue(), registryRequest.getDescription(), registryRequest.getCategory());
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Expenditure: {" + super.toString() + ", category: " + category + "}";
    }
}
