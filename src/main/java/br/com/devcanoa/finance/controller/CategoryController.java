package br.com.devcanoa.finance.controller;

import br.com.devcanoa.finance.domain.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categoria")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(Category.getAllCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable final int id) {
        return ResponseEntity.ok(Category.getCategoryById(id).getValue());
    }
}
