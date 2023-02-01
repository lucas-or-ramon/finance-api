package br.com.devcanoa.finance.api.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "revenue")
public class Revenue extends Registry {

    public Revenue(final ObjectId id, final LocalDate date, final Double value, final String description) {
        super(id, date, value, description);
    }

    @Override
    public String toString() {
        return "Revenue: {" + super.toString() + "}";
    }
}
