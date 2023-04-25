package br.com.devcanoa.finance.api.domain.model;

public class Either<F, S> {

    private final F failure;
    private final S success;
    private final boolean isFailure;

    private Either(final F failure, final S success, final boolean isFailure) {
        this.failure = failure;
        this.success = success;
        this.isFailure = isFailure;
    }

    public static <F, S> Either<F, S> failure(final F failure) {
        return new Either<>(failure, null, true);
    }

    public static <F, S> Either<F, S> success(final S success) {
        return new Either<>(null, success, false);
    }

    public boolean isFailure() {
        return isFailure;
    }

    public boolean isSuccess() {
        return !isFailure;
    }

    public F getFailure() {
        return failure;
    }

    public S getSuccess() {
        return success;
    }
}
