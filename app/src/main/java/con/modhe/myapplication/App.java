package con.modhe.myapplication;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import con.modhe.myapplication.utils.remoteConfig.RequestRemoteConfig;

/**
 * author Created by harrishuang on 2020/11/14.
 * email : huangjinping1000@163.com
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        RequestRemoteConfig.acquireRemoteConfig();
    }

    public static Context getContext() {
        return context;
    }
}
