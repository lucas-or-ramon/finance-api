package br.com.devcanoa.finance.api.repository;

import br.com.devcanoa.finance.api.domain.FinanceDate;
import br.com.devcanoa.finance.api.exception.RegistryAlreadyExistsException;
import br.com.devcanoa.finance.api.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.model.Revenue;
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
public class RevenueRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RevenueRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Revenue> getAllRevenues(String description) {
        if (Objects.isNull(description))
            return mongoTemplate.findAll(Revenue.class);

        Query query = Query.query(Criteria.where("description").regex(description, "i"));
        return mongoTemplate.find(query, Revenue.class);
    }

    public Revenue getRevenueById(ObjectId id) {
        Revenue revenue = mongoTemplate.findById(id, Revenue.class);
        if (Objects.isNull(revenue)) {
            throw new RegistryNotFoundException("Revenue Not Found: " + id);
        }
        return revenue;
    }

    public List<Revenue> getRevenuesByYearAndMonth(LocalDate date) {
        Query query = Query.query(new Criteria().andOperator(
                where("date").gte(FinanceDate.getStartOfMonth(date)),
                where("date").lte(FinanceDate.getEndOfMonth(date))));

        return mongoTemplate.find(query, Revenue.class);
    }

    public Revenue insertRevenue(Revenue revenue) {
        if (revenueExist(revenue)) {
            throw new RegistryAlreadyExistsException("Revenue Already Exists" + revenue.getId());
        }
        return mongoTemplate.save(revenue);
    }

    public boolean revenueExist(Revenue revenue) {
        Query query = Query.query(new Criteria().andOperator(
                where("description").is(revenue.getDescription()),
                where("date").gte(FinanceDate.getStartOfMonth(revenue.getDate())),
                where("date").lte(FinanceDate.getEndOfMonth(revenue.getDate()))));

        return mongoTemplate.exists(query, Revenue.class);
    }

    public Revenue updateRevenue(Revenue revenue) {
        Revenue oldRevenue = getRevenueById(revenue.getId());

        if (oldRevenue.equals(revenue)) {
            return mongoTemplate.save(revenue);
        }
        return insertRevenue(revenue);
    }

    public Revenue deleteRevenue(ObjectId id) {
        Revenue revenue = mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), Revenue.class);
        if (Objects.isNull(revenue)) {
            throw new RegistryNotFoundException("Revenue Not Found: " + id);
        }
        return revenue;
    }
}
