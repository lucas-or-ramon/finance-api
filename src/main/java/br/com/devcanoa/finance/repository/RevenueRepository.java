package br.com.devcanoa.finance.repository;

import br.com.devcanoa.finance.model.Revenue;
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
public class RevenueRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RevenueRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public boolean revenueExist(Revenue revenue) {
        LocalDate date = revenue.getDate();

        Query query = Query.query(new Criteria().andOperator(
                where("description").is(revenue.getDescription()),
                where("date").gte(LocalDate.of(date.getYear(), date.getMonth(), 1)),
                where("date").lte(LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth()))
        ));

        try {
            return mongoTemplate.exists(query, Revenue.class);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Revenue> insertRevenue(Revenue revenue) {
        try {
            return Optional.of(mongoTemplate.save(revenue));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<List<Revenue>> getAllRevenues(String description) {
        try {
            if (Objects.isNull(description))
                return Optional.of(mongoTemplate.findAll(Revenue.class));

            Query query = Query.query(Criteria.where("description").regex(description, "i"));
            return Optional.of(mongoTemplate.find(query, Revenue.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Revenue> getRevenueById(ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, Revenue.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Revenue> updateRevenue(Revenue revenue) {
        try {
            return Optional.of(mongoTemplate.save(revenue));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Revenue> deleteRevenue(ObjectId id) {
        try {
            return Optional.ofNullable(mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), Revenue.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<List<Revenue>> getRevenuesByYearAndMonth(LocalDate date) {
        Query query = Query.query(new Criteria().andOperator(
                where("date").gte(LocalDate.of(date.getYear(), date.getMonth(), 1)),
                where("date").lte(LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth()))
        ));
        try {
            return Optional.of(mongoTemplate.find(query, Revenue.class));
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
