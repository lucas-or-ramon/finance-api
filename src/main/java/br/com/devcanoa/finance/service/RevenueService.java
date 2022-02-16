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

    public Revenue insertRevenue(Revenue revenue) {
        if (revenueRepository.revenueExist(revenue)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return revenueRepository.insertRevenue(revenue)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<Revenue> getAllRevenues(String description) {
        return revenueRepository.getAllRevenues(description)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public Revenue getRevenueById(ObjectId id) {
        return revenueRepository.getRevenueById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public Revenue updateRevenue(Revenue newRevenue) {
        Revenue oldRevenue = getRevenueById(newRevenue.getId());

        if (oldRevenue.equals(newRevenue)) {
            return revenueRepository.updateRevenue(newRevenue).orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
        } else {
            return insertRevenue(newRevenue);
        }
    }

    public Revenue deleteRevenue(ObjectId id) {
        return revenueRepository.deleteRevenue(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<Revenue> getRevenuesByYearAndMonth(LocalDate date) {
        return revenueRepository.getRevenuesByYearAndMonth(date)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
