package br.com.devcanoa.finance.api.adapter.outbound.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.CategoryEntity;
import br.com.devcanoa.finance.api.domain.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static br.com.devcanoa.finance.api.adapter.outbound.repository.query.QueryMongoRegistry.whereNameIs;

public class CategoryRepositoryMongo implements CategoryRepository {

    private final MongoTemplate mongoTemplate;

    public CategoryRepositoryMongo(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<String> getByName(final String name) {
        try {
            final var categories = mongoTemplate.find(whereNameIs(name), CategoryEntity.class);
            return categories.stream().findFirst().map(CategoryEntity::name);
        } catch (final Exception e) {
            throw new RuntimeException("Error when trying to find category by name: " + name, e);
        }
    }

    @Override
    public String save(final String name) {
        try {
            final var category = new CategoryEntity(new ObjectId(), name);
            return mongoTemplate.save(category).name();
        } catch (final Exception e) {
            throw new RuntimeException("Error when trying to save category: " + name, e);
        }
    }
}
