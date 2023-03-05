package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.CreditCardEntity;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public interface CreditCardEntityMapper {

    static CreditCard mapToDomain(final CreditCardEntity entity) {
        return nonNull(entity) ? new CreditCard(entity.id().toString(), entity.name(), entity.dueDate()) : null;
    }

    static CreditCardEntity mapToEntity(final CreditCard creditCard) {
        return new CreditCardEntity(new ObjectId(creditCard.id()), creditCard.name(), creditCard.dueDate());
    }
}
