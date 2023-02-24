package br.com.devcanoa.finance.api.domain.adapter.mapper.request;

import br.com.devcanoa.finance.api.domain.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public interface RequestRegistryMapper<T extends Registry> {

    T mapper(Pair<ObjectId, RegistryRequest> requestPair);
}
