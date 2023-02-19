package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.*;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MonthlyResumeService {

    private final CreditCardService creditCardService;
    private final MongoRepository<Revenue> revenueRepository;
    private final MongoRepository<Expenditure> expenditureRepository;

    public MonthlyResumeService(final CreditCardService creditCardService,
                                final MongoRepository<Revenue> revenueRepository,
                                final MongoRepository<Expenditure> expenditureRepository) {
        this.creditCardService = creditCardService;
        this.revenueRepository = revenueRepository;
        this.expenditureRepository = expenditureRepository;
    }

    public MonthlyResumeResponse getMonthlyResume(final LocalDate date) {
        final var revenue = getAllRevenuesBy(date);
        final var expenditure = getAllExpendituresBy(date);
        final var creditCard = getAllCreditCardBy(expenditure);
        final double balance = revenue.total() - expenditure.total();
        return new MonthlyResumeResponse(date, balance, revenue, expenditure, creditCard);
    }

    private List<CreditCardResponse> getAllCreditCardBy(final ResumeResponse expenditure) {
        final HashMap<String, Double> creditCardTotal = new HashMap<>();
        expenditure.registries().stream()
                .filter(r -> Objects.nonNull(r.creditCardId()))
                .collect(Collectors.groupingBy(RegistryResponse::creditCardId))
                .forEach((key, value) -> creditCardTotal.put(key, value.stream().mapToDouble(RegistryResponse::value).sum()));

        final var creditCardResponse = new ArrayList<CreditCardResponse>();
        final var responseMapper = new CreditCardResponseMapper();
        creditCardTotal.forEach((id, total) -> {
            final var response = creditCardService.findById(new ObjectId(id))
                    .map(c -> responseMapper.apply(Map.entry(total, c)))
                    .orElse(null);
            if (Objects.nonNull(response)) {
                creditCardResponse.add(response);
            }
        });
        return creditCardResponse;
    }

    private ResumeResponse getAllRevenuesBy(final LocalDate date) {
        return new ResumeResponseMapper<Revenue>(new RegistryResponseMapper<>()).apply(revenueRepository.getByDate(date));
    }

    private ResumeResponse getAllExpendituresBy(final LocalDate date) {
        return new ResumeResponseMapper<Expenditure>(new RegistryResponseMapper<>()).apply(expenditureRepository.getByDate(date));
    }
}
