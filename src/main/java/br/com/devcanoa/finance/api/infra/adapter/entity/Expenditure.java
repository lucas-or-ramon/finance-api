package br.com.devcanoa.finance.api.infra.adapter.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "expenditure")
public class Expenditure extends Registry {

    public Expenditure(final ObjectId id, final LocalDate date, final Double value, final String description, final ObjectId creditCardId, final Recurrency recurrency) {
        super(id, date, value, description, creditCardId, recurrency);
    }

    @Override
    public String toString() {
        return "Expenditure: {" + super.toString() + "}";
    }
}
