package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.CreditCard;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credit-card")
public record CreditCardEntity(@Id ObjectId id, String name, int dueDate) {

    public CreditCard mapToDomain() {
        return new CreditCard(id.toString(), name, dueDate);
    }

    public static CreditCardEntity mapToEntity(final CreditCard creditCard) {
        return new CreditCardEntity(new ObjectId(creditCard.id()), creditCard.name(), creditCard.dueDate());
    }
}
