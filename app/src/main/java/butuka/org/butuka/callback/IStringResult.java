package butuka.org.butuka.callback;

/**
 * Created by iagobelo on 22/06/16.
 */
public interface IStringResult {
    void onSuccess(String s);

    void onFailed(Exception e);
}
