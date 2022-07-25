package br.com.devcanoa.finance.api.domain;

public class CategoryResume {
    private final String category;
    private final Double total;

    public CategoryResume(String category, Double total) {
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
