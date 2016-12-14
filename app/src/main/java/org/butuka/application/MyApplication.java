package org.butuka.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
