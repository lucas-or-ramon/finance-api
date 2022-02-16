package br.com.devcanoa.finance.repository;

import br.com.devcanoa.finance.model.Expenditure;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class ExpenditureRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ExpenditureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public boolean expenditureExist(Expenditure expenditure) {
        LocalDate date = expenditure.getDate();

        Query query = Query.query(new Criteria().andOperator(
                where("description").is(expenditure.getDescription()),
                where("date").gte(LocalDate.of(date.getYear(), date.getMonth(), 1)),
                where("date").lte(LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth()))
        ));

        try {
            return mongoTemplate.exists(query, Expenditure.class);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Expenditure> insertExpenditure(Expenditure expenditure) {
        try {
            return Optional.of(mongoTemplate.insert(expenditure));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<List<Expenditure>> getAllExpenditures(String description) {
        try {
            if (Objects.isNull(description))
                return Optional.of(mongoTemplate.findAll(Expenditure.class));

            Query query = Query.query(Criteria.where("description").regex(description, "i"));
            return Optional.of(mongoTemplate.find(query, Expenditure.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Expenditure> getExpenditureById(ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, Expenditure.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Expenditure> updateExpenditure(Expenditure expenditure) {
        try {
            return Optional.of(mongoTemplate.save(expenditure));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Expenditure> deleteExpenditure(ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), Expenditure.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<List<Expenditure>> getExpendituresByYearAndMonth(LocalDate date) {
        Query query = Query.query(new Criteria().andOperator(
                where("date").gte(LocalDate.of(date.getYear(), date.getMonth(), 1)),
                where("date").lte(LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth()))
        ));
        try {
            return Optional.of(mongoTemplate.find(query, Expenditure.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
