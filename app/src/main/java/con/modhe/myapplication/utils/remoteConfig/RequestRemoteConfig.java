package con.modhe.myapplication.utils.remoteConfig;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import con.modhe.myapplication.R;

/**
 * author Created by harrishuang on 2020/12/2.
 * email : huangjinping1000@163.com
 */
public class RequestRemoteConfig {
    private static EchoServer sEchoServer;
    private static ScheduledExecutorService sExecutor;

    public static FirebaseRemoteConfig getRemoteConfig() {
        if (sRemoteConfig == null) {
            sRemoteConfig = FirebaseRemoteConfig.getInstance();
        }
        return sRemoteConfig;
    }

    private static FirebaseRemoteConfig sRemoteConfig;

    public static void acquireRemoteConfig() {
        sRemoteConfig = FirebaseRemoteConfig.getInstance();


//获取配置实例
        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//创建配置setting,可以在此设置开发者模式，增加获取成功率
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_default);
//        sRemoteConfig.setDefaults(R.xml.remote_config_default);
        long cacheExpiration = 20 * 60;
//        if (sRemoteConfig.getInfo().getConfigSettings().getMinimumFetchIntervalInSeconds()) {
//            cacheExpiration = 0;
//        }

        cacheExpiration = 0;

        sRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            sRemoteConfig.fetchAndActivate();
                        } else {

                        }
                    }
                });
    }

    /**
     * 以固定周期频率执行任务
     */
    public static void executeFixedRate() {
        if (sExecutor == null) {
            sExecutor = Executors.newScheduledThreadPool(1);
        }
        if (sEchoServer == null) {
            sEchoServer = new EchoServer();
        }
        sExecutor.scheduleAtFixedRate(
                sEchoServer,
                0,
                20,
                TimeUnit.MINUTES);
    }

    private static class EchoServer implements Runnable {

        @Override
        public void run() {
            // acquire a remote config
            RequestRemoteConfig.acquireRemoteConfig();
        }
    }
}
