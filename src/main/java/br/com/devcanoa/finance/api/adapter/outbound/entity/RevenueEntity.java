package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "revenue")
public class RevenueEntity extends RegistryEntity {

    public RevenueEntity(final ObjectId id,
                         final Double value,
                         final String category,
                         final String description,
                         final ObjectId creditCardId,
                         final Recurrence recurrence) {
        super(id, value, category, description, creditCardId, recurrence);
    }
}
