package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.model.CreditCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardService extends MongoRepository<CreditCard, ObjectId> {
}
