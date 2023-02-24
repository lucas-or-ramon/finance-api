package br.com.devcanoa.finance.api.domain.port.service;

import br.com.devcanoa.finance.api.domain.dto.request.CreditCardRequest;
import br.com.devcanoa.finance.api.domain.dto.response.CreditCardResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface CreditCardService {

    List<CreditCardResponse> listAll();
    CreditCardResponse getById(final ObjectId id);
    void insert(final CreditCardRequest creditCardRequest);
    void update(final ObjectId id, final CreditCardRequest creditCardRequest);
    void delete(final ObjectId id);
}
