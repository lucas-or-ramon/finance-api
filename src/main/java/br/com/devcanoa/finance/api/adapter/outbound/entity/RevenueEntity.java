package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "revenue")
public class RevenueEntity extends RegistryEntity {

    public RevenueEntity(final ObjectId id, final Double value, final String description, final ObjectId creditCardId, final RecurrenceEntity recurrence) {
        super(id, value, description, creditCardId, recurrence);
    }

    @Override
    public String toString() {
        return "Revenue: {" + super.toString() + "}";
    }
}
