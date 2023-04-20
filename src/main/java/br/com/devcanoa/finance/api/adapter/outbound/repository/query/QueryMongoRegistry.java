package br.com.devcanoa.finance.api.adapter.outbound.repository.query;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
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

    public static Query whereRecurrenceHas(final FinanceDate date) {
        return query(new Criteria().andOperator(
                        where("recurrence.start.year").lte(date.year()),
                        where("recurrence.start.month").lte(date.month()),
                        where("recurrence.end.year").gte(date.year()),
                        where("recurrence.end.month").gte(date.month())));
    }
}
