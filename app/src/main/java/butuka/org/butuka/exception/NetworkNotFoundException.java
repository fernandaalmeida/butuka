package butuka.org.butuka.exception;

/**
 * Created by iagobelo on 27/09/2016.
 */
public class NetworkNotFoundException extends Exception {
    public NetworkNotFoundException(String s) {
        super(s);
    }

    public NetworkNotFoundException() {
    }

    public NetworkNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}
