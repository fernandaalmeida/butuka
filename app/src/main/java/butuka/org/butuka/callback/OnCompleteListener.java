package butuka.org.butuka.callback;

import butuka.org.butuka.model.Task;

/**
 * Created by iagobelo on 04/10/2016.
 */

public interface OnCompleteListener<T> {
    void onComplete(Task<T> task);
}
