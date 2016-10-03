package butuka.org.butuka.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by iagobelo on 28/09/2016.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        MyApplication.mContext = getApplicationContext();
    }

    public static Context getContext() {
        return MyApplication.mContext;
    }
}
