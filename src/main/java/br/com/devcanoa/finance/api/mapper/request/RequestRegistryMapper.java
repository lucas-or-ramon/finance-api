package br.com.devcanoa.finance.api.mapper.request;

import br.com.devcanoa.finance.api.domain.request.RegistryRequest;
import br.com.devcanoa.finance.api.model.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public interface RequestRegistryMapper<T extends Registry> {

    T mapper(Pair<ObjectId, RegistryRequest> requestPair);
}
