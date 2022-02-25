package br.com.devcanoa.finance.model;

import br.com.devcanoa.finance.controller.request.RegistryRequest;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "revenue")
public class Revenue extends Registry {

    @PersistenceConstructor
    private Revenue(ObjectId id, LocalDate date, Double value, String description) {
        super(id, date, value, description);
    }

    private Revenue(final RegistryRequest registryRequest) {
        super(registryRequest);
    }

    public static Revenue from(final RegistryRequest registryRequest) {
        return new Revenue(registryRequest);
    }

    public static Revenue withId(final ObjectId id, final RegistryRequest registryRequest) {
        return new Revenue(id, registryRequest.getDate(), registryRequest.getValue(), registryRequest.getDescription());
    }

    @Override
    public String toString() {
        return "Revenue: {" + super.toString() + "}";
    }
}
