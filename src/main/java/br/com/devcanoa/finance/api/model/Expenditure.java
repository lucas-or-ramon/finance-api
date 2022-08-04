package br.com.devcanoa.finance.api.model;

import br.com.devcanoa.finance.api.controller.request.RegistryRequest;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "expenditure")
public class Expenditure extends Registry {
    @PersistenceConstructor
    private Expenditure(ObjectId id, LocalDate date, Double value, String description) {
        super(id, date, value, description);
    }

    private Expenditure(final RegistryRequest registryRequest) {
        super(registryRequest);
    }

    public static Expenditure from(final RegistryRequest registryRequest) {
        return new Expenditure(registryRequest);
    }

    public static Expenditure withId(final ObjectId id, final RegistryRequest registryRequest) {
        return new Expenditure(id, registryRequest.getDate(), registryRequest.getValue(), registryRequest.getDescription());
    }

    @Override
    public String toString() {
        return "Expenditure: {" + super.toString()  + "}";
    }
}
