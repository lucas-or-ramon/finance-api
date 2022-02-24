package br.com.devcanoa.finance.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Category {
    HOME (2, "MORADIA"),
    FOOD (0, "ALIMENTAÇÃO"),
    OTHER (7, "OUTROS"),
    HEALTH (1, "SAÚDE"),
    LEISURE (5, "LAZER"),
    TRANSPORT (3, "TRANSPORTE"),
    EDUCATION (4, "EDUCAÇÃO"),
    UNEXPECTED (6, "IMPREVISTOS");

    private final int id;
    private final String value;

    Category(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static Category getCategoryById(int id) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public static Category getCategoryByValue(String value) {
        return Arrays.stream(Category.values())
                .filter(category -> Objects.equals(category.getValue(), value))
                .findFirst()
                .orElseThrow();
    }

    public static List<String> getAllCategories() {
        return Arrays.stream(Category.values()).map(Category::getValue).collect(Collectors.toList());
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }
}
