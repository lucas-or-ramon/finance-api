package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.AnnualResumeResponse;
import br.com.devcanoa.finance.api.controller.response.MonthlyResumeResponse;
import br.com.devcanoa.finance.api.exception.FinanceException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

@Service
public class AnnualResumeService {

    private final TaskExecutor taskExecutor;
    private final MonthlyResumeService resumeService;

    public AnnualResumeService(final TaskExecutor taskExecutor, final MonthlyResumeService resumeService) {
        this.taskExecutor = taskExecutor;
        this.resumeService = resumeService;
    }

    public AnnualResumeResponse getAnnualResume(final LocalDate date) {
        final var completableFutures = IntStream.rangeClosed(0, 11)
                .mapToObj(i -> getCompletableFutures(date.minusMonths(i))).toList();
        return run(completableFutures);
    }

    private CompletableFuture<MonthlyResumeResponse> getCompletableFutures(final LocalDate date) {
        return CompletableFuture.supplyAsync(() -> resumeService.getMonthlyResume(date), taskExecutor);
    }

    private AnnualResumeResponse run(final List<CompletableFuture<MonthlyResumeResponse>> completableFutures) {
        try {
            final var monthlyResumes = getMonthlyResumes(completableFutures).get(1000, TimeUnit.MILLISECONDS);
            return new AnnualResumeResponse(monthlyResumes);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FinanceException("Thread Interrupted", e);
        } catch (final ExecutionException | TimeoutException e) {
            throw new FinanceException("Execution or Timeout Exception", e);
        }
    }

    private CompletableFuture<List<MonthlyResumeResponse>> getMonthlyResumes(final List<CompletableFuture<MonthlyResumeResponse>> completableFutures) {
        return CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[0]))
                .thenApply(r -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .toList());
    }
}
