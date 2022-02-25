package br.com.devcanoa.finance.repository;

import br.com.devcanoa.finance.domain.FinanceDate;
import br.com.devcanoa.finance.domain.exception.RegistryAlreadyExistsException;
import br.com.devcanoa.finance.domain.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.model.Expenditure;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class ExpenditureRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ExpenditureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Expenditure> getAllExpenditures(String description) {
        if (Objects.isNull(description))
            return mongoTemplate.findAll(Expenditure.class);

        Query query = Query.query(Criteria.where("description").regex(description, "i"));
        return (mongoTemplate.find(query, Expenditure.class));
    }

    public Expenditure getExpenditureById(ObjectId id) {
        Expenditure expenditure = mongoTemplate.findById(id, Expenditure.class);
        if (Objects.isNull(expenditure)) {
            throw new RegistryNotFoundException("Expenditure Not Found: " + id);
        }
        return expenditure;
    }

    public List<Expenditure> getExpendituresByYearAndMonth(LocalDate date) {
        Query query = Query.query(new Criteria().andOperator(
                where("date").gte(FinanceDate.getStartOfMonth(date)),
                where("date").lte(FinanceDate.getEndOfMonth(date))));

        return mongoTemplate.find(query, Expenditure.class);
    }

    public Expenditure insertExpenditure(Expenditure expenditure) {
        if (expenditureExist(expenditure)) {
            throw new RegistryAlreadyExistsException("Expenditure Already Exists: " + expenditure.getId());
        }
        return mongoTemplate.save(expenditure);
    }

    public boolean expenditureExist(Expenditure expenditure) {
        Query query = Query.query(new Criteria().andOperator(
                where("description").is(expenditure.getDescription()),
                where("date").gte(FinanceDate.getStartOfMonth(expenditure.getDate())),
                where("date").lte(FinanceDate.getEndOfMonth(expenditure.getDate()))));

        return (mongoTemplate.exists(query, Expenditure.class));
    }

    public Expenditure updateExpenditure(Expenditure expenditure) {
        Expenditure oldExpenditure = getExpenditureById(expenditure.getId());

        if (oldExpenditure.equals(expenditure)) {
            return mongoTemplate.save(expenditure);
        }
        return insertExpenditure(expenditure);
    }

    public Expenditure deleteExpenditure(ObjectId id) {
        Expenditure expenditure = mongoTemplate.findAndRemove(Query.query(where("id").is(id)), Expenditure.class);
        if (Objects.isNull(expenditure)) {
            throw new RegistryNotFoundException("Expenditure Not Found: " + id);
        }
        return expenditure;
    }
}
