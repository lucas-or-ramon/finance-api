package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.adapter.inbound.dto.Response;
import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import br.com.devcanoa.finance.api.domain.model.Monthly;
import br.com.devcanoa.finance.api.domain.service.AnnualResumeService;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;


public class AnnualResumeServiceImpl implements AnnualResumeService {

    private final TaskExecutor taskExecutor;
    private final MonthlyService monthlyService;

    public AnnualResumeServiceImpl(final TaskExecutor taskExecutor, final MonthlyService monthlyService) {
        this.taskExecutor = taskExecutor;
        this.monthlyService = monthlyService;
    }

    @Override
    public Response.AnnualDto annualResume(final Request.FinanceDateDto date) {
        final var completableFutures = IntStream.rangeClosed(0, 11)
                .mapToObj(month -> getCompletableFutures(date.minusMonths(month))).toList();
        return run(completableFutures);
    }

    private CompletableFuture<Monthly> getCompletableFutures(final Request.FinanceDateDto date) {
        return CompletableFuture.supplyAsync(() -> monthlyService.getMonthlyResume(date), taskExecutor);
    }

    private Response.AnnualDto run(final List<CompletableFuture<Monthly>> completableFutures) {
        try {
            final var monthlyResumes = getMonthlyResumes(completableFutures).get(1000, TimeUnit.MILLISECONDS);
            return Response.AnnualDto.mapToResponse(monthlyResumes);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FinanceException("Thread Interrupted", e);
        } catch (final ExecutionException | TimeoutException e) {
            throw new FinanceException("Execution or Timeout Exception", e);
        }
    }

    private CompletableFuture<List<Monthly>> getMonthlyResumes(final List<CompletableFuture<Monthly>> completableFutures) {
        return CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[0]))
                .thenApply(r -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .toList());
    }
}
