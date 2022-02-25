package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        logger.info("Category");
        return ResponseEntity.ok(Category.getAllCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable final int id) {
        logger.info("Category: {id: {}}", id);
        return ResponseEntity.ok(Category.getCategoryById(id).getValue());
    }
}
