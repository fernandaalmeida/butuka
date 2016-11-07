package butuka.org.butuka.exception;

/**
 * Created by iagobelo on 06/11/2016.
 */

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String s) {
        super(s);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
