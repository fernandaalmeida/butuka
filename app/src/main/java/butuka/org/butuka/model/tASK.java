package butuka.org.butuka.model;

/**
 * Created by iagobelo on 24/10/2016.
 */

public class Task {
    private final boolean isSuccessful;
    private final String message;
    private final Exception exception;

    private Task(Builder builder) {
        this.isSuccessful = builder.isSuccessful;
        this.message = builder.message;
        this.exception = builder.exception;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }

    public static class Builder {
        private final boolean isSuccessful;
        private String message;
        private Exception exception;

        public Builder(boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withExcpetion(Exception e) {
            this.exception = e;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
