package br.com.devcanoa.finance.model;

public class CategorySummary {
    private final String category;
    private final Double total;

    public CategorySummary(String category, Double total) {
        this.category = category;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public Double getTotal() {
        return total;
    }
}
