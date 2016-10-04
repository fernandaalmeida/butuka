package butuka.org.butuka.callback;

import butuka.org.butuka.exception.NetworkNotFoundException;

/**
 * Created by iagobelo on 04/10/2016.
 */

public interface IResult {
    void success();

    void onFailed(String s);
}
