package br.com.devcanoa.finance.api.domain.adapter.service;

import br.com.devcanoa.finance.api.domain.dto.response.AnnualResponse;
import br.com.devcanoa.finance.api.domain.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import br.com.devcanoa.finance.api.domain.port.service.AnnualResumeService;
import br.com.devcanoa.finance.api.domain.port.service.MonthlyResumeService;
import org.springframework.core.task.TaskExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;


public class AnnualResumeServiceImpl implements AnnualResumeService {

    private final TaskExecutor taskExecutor;
    private final MonthlyResumeService monthlyService;

    public AnnualResumeServiceImpl(final TaskExecutor taskExecutor, final MonthlyResumeService monthlyService) {
        this.taskExecutor = taskExecutor;
        this.monthlyService = monthlyService;
    }

    @Override
    public AnnualResponse getResume(final LocalDate date) {
        final var completableFutures = IntStream.rangeClosed(0, 11)
                .mapToObj(i -> getCompletableFutures(date.minusMonths(i))).toList();
        return run(completableFutures);
    }

    private CompletableFuture<MonthlyResponse> getCompletableFutures(final LocalDate date) {
        return CompletableFuture.supplyAsync(() -> monthlyService.getResume(date), taskExecutor);
    }

    private AnnualResponse run(final List<CompletableFuture<MonthlyResponse>> completableFutures) {
        try {
            final var monthlyResumes = getMonthlyResumes(completableFutures).get(1000, TimeUnit.MILLISECONDS);
            return new AnnualResponse(monthlyResumes);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FinanceException("Thread Interrupted", e);
        } catch (final ExecutionException | TimeoutException e) {
            throw new FinanceException("Execution or Timeout Exception", e);
        }
    }

    private CompletableFuture<List<MonthlyResponse>> getMonthlyResumes(final List<CompletableFuture<MonthlyResponse>> completableFutures) {
        return CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[0]))
                .thenApply(r -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .toList());
    }
}
