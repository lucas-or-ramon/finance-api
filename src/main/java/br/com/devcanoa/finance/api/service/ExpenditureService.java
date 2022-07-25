package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.repository.ExpenditureRepository;
import br.com.devcanoa.finance.api.model.Expenditure;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;

    @Autowired
    public ExpenditureService(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    public List<Expenditure> getAllExpenditures(String description) {
        return expenditureRepository.getAllExpenditures(description);
    }

    public Expenditure getExpenditureById(ObjectId id) {
        return expenditureRepository.getExpenditureById(id);
    }

    public List<Expenditure> getExpendituresByYearAndMonth(LocalDate date) {
        return expenditureRepository.getExpendituresByYearAndMonth(date);
    }

    public Expenditure insertExpenditure(Expenditure expenditure) {
        return expenditureRepository.insertExpenditure(expenditure);
    }

    public Expenditure updateExpenditure(Expenditure expenditure) {
        return expenditureRepository.updateExpenditure(expenditure);
    }

    public Expenditure deleteExpenditure(ObjectId id) {
        return expenditureRepository.deleteExpenditure(id);
    }
}
