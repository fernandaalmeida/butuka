package butuka.org.butuka.callback;

/**
 * Created by iagobelo on 28/09/2016.
 */

public interface IDAOResult {
    void onSuccess(String s);

    void onFailed(Exception e);
}
