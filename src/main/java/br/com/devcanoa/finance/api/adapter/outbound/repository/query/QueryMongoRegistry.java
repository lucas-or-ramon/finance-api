package br.com.devcanoa.finance.api.adapter.outbound.repository.query;

import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public final class QueryMongoRegistry {

    private QueryMongoRegistry() {
    }

    public static Query whereDescriptionIs(final String description) {
        return Query.query(Criteria.where("description").regex(description, "i"));
    }

    public static <T extends RegistryEntity> Query whereRegistryIs(final T registry) {
        return Query.query(new Criteria()
                .andOperator(
                        where("description").is(registry.getDescription()),
                        where("date").gte(FinanceDate.getStartOfMonth(registry.getDate())),
                        where("date").lte(FinanceDate.getEndOfMonth(registry.getDate()))));
    }

    public static Query whereDateIs(final LocalDate date) {
        return Query.query(new Criteria()
                .andOperator(
                        where("date").gte(FinanceDate.getStartOfMonth(date)),
                        where("date").lte(FinanceDate.getEndOfMonth(date))));
    }
}
