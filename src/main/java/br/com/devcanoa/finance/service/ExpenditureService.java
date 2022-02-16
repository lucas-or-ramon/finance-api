package br.com.devcanoa.finance.service;

import br.com.devcanoa.finance.model.Expenditure;
import br.com.devcanoa.finance.repository.ExpenditureRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;

    @Autowired
    public ExpenditureService(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    public Expenditure insertExpenditure(Expenditure expenditure) {
        if (expenditureRepository.expenditureExist(expenditure)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return expenditureRepository.insertExpenditure(expenditure)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<Expenditure> getAllExpenditures(String description) {
        return expenditureRepository.getAllExpenditures(description)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public Expenditure getExpenditureById(ObjectId id) {
        return expenditureRepository.getExpenditureById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public Expenditure updateExpenditure(Expenditure newExpenditure) {
        Expenditure oldExpenditure = getExpenditureById(newExpenditure.getId());

        if (oldExpenditure.equals(newExpenditure)) {
            return expenditureRepository.updateExpenditure(newExpenditure)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
        } else {
            return insertExpenditure(newExpenditure);
        }
    }

    public Expenditure deleteExpenditure(ObjectId id) {
        Optional<Expenditure> optionalExpenditure = expenditureRepository.deleteExpenditure(id);
        return optionalExpenditure
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<Expenditure> getExpendituresByYearAndMonth(LocalDate date) {
        return expenditureRepository.getExpendituresByYearAndMonth(date)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
