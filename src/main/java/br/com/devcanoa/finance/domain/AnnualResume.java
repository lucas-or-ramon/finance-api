package br.com.devcanoa.finance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnualResume {
    private final List<MonthlyResume> monthlyResume = new ArrayList<>();
    private final List<CategoryResume> annualCategoriesResume = new ArrayList<>();

    public void add(MonthlyResume monthlyResume) {
        this.monthlyResume.add(monthlyResume);
    }

    private void add(CategoryResume categoryResume) {
        this.annualCategoriesResume.add(categoryResume);
    }

    public void setAnnualCategoriesResume() {
        this.monthlyResume.stream()
                .flatMap(monthlyResume -> monthlyResume.getCategoryResume().stream())
                .collect(Collectors.groupingBy(CategoryResume::getCategory))
                .forEach((key, value) -> add(new CategoryResume(key, value.stream().mapToDouble(CategoryResume::getTotal).sum())));
    }

    public List<MonthlyResume> getMonthlyResume() {
        return monthlyResume;
    }

    public List<CategoryResume> getAnnualCategoriesResume() {
        return annualCategoriesResume;
    }
}
