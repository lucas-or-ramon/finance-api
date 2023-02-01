package br.com.devcanoa.finance.api.controller.request;

import br.com.devcanoa.finance.api.model.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;

public interface RegistryRequestMapper<T extends Registry> {

    T mapper(Pair<ObjectId, RegistryRequest> requestPair);
}
