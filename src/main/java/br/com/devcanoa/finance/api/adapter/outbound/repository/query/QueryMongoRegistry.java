package br.com.devcanoa.finance.api.adapter.outbound.repository.query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public final class QueryMongoRegistry {

    private QueryMongoRegistry() {
    }

    public static Query whereDescriptionIs(final String description) {
        return query(where("description").regex(description, "i"));
    }

    public static Query whereRecurrenceHas(final int dateAsNumericValue) {
        return query(new Criteria().andOperator(
                where("recurrence.start").lte(dateAsNumericValue),
                where("recurrence.end").gte(dateAsNumericValue)));
    }

    public static Query whereNameIs(final String name) {
        return query(where("name").is(name));
    }
}
