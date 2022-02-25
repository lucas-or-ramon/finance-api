package br.com.devcanoa.finance.service;

import br.com.devcanoa.finance.model.Revenue;
import br.com.devcanoa.finance.repository.RevenueRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueService {

    private final RevenueRepository revenueRepository;

    @Autowired
    public RevenueService(RevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
    }

    public List<Revenue> getAllRevenues(String description) {
        return revenueRepository.getAllRevenues(description);
    }

    public Revenue getRevenueById(ObjectId id) {
        return revenueRepository.getRevenueById(id);
    }

    public List<Revenue> getRevenuesByYearAndMonth(LocalDate date) {
        return revenueRepository.getRevenuesByYearAndMonth(date);
    }

    public Revenue insertRevenue(Revenue revenue) {
        return revenueRepository.insertRevenue(revenue);
    }

    public Revenue updateRevenue(Revenue newRevenue) {
        return revenueRepository.updateRevenue(newRevenue);
    }

    public Revenue deleteRevenue(ObjectId id) {
        return revenueRepository.deleteRevenue(id);
    }
}
