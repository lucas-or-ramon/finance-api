package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenditure")
public class ExpenditureEntity extends RegistryEntity {

    public ExpenditureEntity(final ObjectId id, final Double value, final String description, final ObjectId creditCardId, final RecurrenceEntity recurrence) {
        super(id, value, description, creditCardId, recurrence);
    }

    @Override
    public String toString() {
        return "Expenditure: {" + super.toString() + "}";
    }
}
