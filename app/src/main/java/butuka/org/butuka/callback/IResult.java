package butuka.org.butuka.callback;

import butuka.org.butuka.exception.NetworkNotFoundException;

/**
 * Created by iagobelo on 04/10/2016.
 */

public interface IResult {
    void result(String s);

    void onFailed(Exception e);
}
