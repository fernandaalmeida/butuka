package butuka.org.butuka.model;

/**
 * Created by iagobelo on 24/10/2016.
 */

public class Task<T> {
    private boolean isSuccessful;
    private Exception exception;
    private T result;

    public Task(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public Task(boolean isSuccessful, Exception exception) {
        this.isSuccessful = isSuccessful;
        this.exception = exception;
    }

    public Task(boolean isSuccessful, T result) {
        this.isSuccessful = isSuccessful;
        this.exception = exception;
        this.result = result;
    }

    public Task(Task task) {
        this.isSuccessful = task.isSuccessful();
        this.exception = task.getException();
        this.result = (T) task.getResult();
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Exception getException() {
        return exception;
    }

    public T getResult() {
        return result;
    }
}
