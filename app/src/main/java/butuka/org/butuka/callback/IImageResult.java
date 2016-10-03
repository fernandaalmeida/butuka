package butuka.org.butuka.callback;

import android.graphics.Bitmap;

/**
 * Created by iagobelo on 24/06/16.
 */
public interface IImageResult {

    void onSuccess(Bitmap bitmap);

    void onFailed(Exception e);
}
