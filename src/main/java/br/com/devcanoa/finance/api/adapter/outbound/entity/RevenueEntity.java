package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "revenue")
public class RevenueEntity extends RegistryEntity {

    public RevenueEntity(final ObjectId id, final LocalDate date, final Double value, final String description, final ObjectId creditCardId, final RecurrencyEntity recurrencyEntity) {
        super(id, date, value, description, creditCardId, recurrencyEntity);
    }

    @Override
    public String toString() {
        return "Revenue: {" + super.toString() + "}";
    }
}
