package con.modhe.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import con.modhe.myapplication.utils.FCMHelper;
import con.modhe.myapplication.utils.remoteConfig.RequestRemoteConfig;

/**
 * author Created by harrishuang on 2020/11/14.
 * email : huangjinping1000@163.com
 */
public class LancherAtivity extends AppCompatActivity {
    final String TAG = "Messaging";

    TextView txt_message;

    private FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();


    @Override
    protected void onResume() {
        super.onResume();
        FCMHelper fcmHelper=new FCMHelper();
        fcmHelper.getFirebaseMessaging();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancher);
        txt_message = (TextView) findViewById(R.id.txt_message);

//        initFirebase();

        Log.d("okhttp", "=======");
        Log.d("okhttp", getAndroidId(this));
        Log.d("okhttp", "=======");


    }

    @SuppressLint({"HardwareIds"})
    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public void onClickView(View view) {
        Log.d("okhttp", "view");
//        logger.logEvent("sentFriendRequest");
        FirebaseLog.getInstance().report("LancherAtivity");
        FaceBookLog.getInstance().report(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL);
//        Intent intent = new Intent(this, FBLogActivity.class);
//        startActivity(intent);

        initFirebase();
    }


    public void onFirebaseRemote(View view) {
        firebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        firebaseRemoteConfig.activate();
                        firebaseRemoteConfig.fetchAndActivate();
                        Log.d(TAG, "my_key: " + firebaseRemoteConfig.getString("my_key"));
                        Log.d(TAG, "key2: " + RequestRemoteConfig.getRemoteConfig().getString("key2"));
                        Log.d(TAG, "key3: " + RequestRemoteConfig.getRemoteConfig().getString("key3"));
                        Log.d(TAG, "keydemo: " + RequestRemoteConfig.getRemoteConfig().getString("keydemo"));

                    }
                });
        Log.d(TAG, "key2: " + RequestRemoteConfig.getRemoteConfig().getString("key2"));
        Log.d(TAG, "key3: " + RequestRemoteConfig.getRemoteConfig().getString("key3"));
        Log.d(TAG, "my_key: " + RequestRemoteConfig.getRemoteConfig().getString("my_key"));
        Log.d(TAG, "keydemo: " + RequestRemoteConfig.getRemoteConfig().getString("keydemo"));


    }


    private void initFirebase() {
        FirebaseInstallations.getInstance().getToken(/* forceRefresh */true)
                .addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstallationTokenResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Log.d("Installations", "Installation auth token: " + task.getResult().getToken());
                        } else {
                            Log.e("Installations", "Unable to get Installation auth token");
                        }
                    }
                });

//        FirebaseInstallations.getInstance().getToken(/* forceRefresh */true)
//                .addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstallationTokenResult> task) {
//                        if (task.isSuccessful() && task.getResult() != null) {
//                            Log.d("Installations", "Installation auth token: " + task.getResult().getToken());
//                        } else {
//                            Log.e("Installations", "Unable to get Installation auth token");
//                        }
//                    }
//                });
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//
//                        // Log and toast
////                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d(TAG, token);
////                        Toast.makeText(LancherAtivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();

                        txt_message.setText(token);
                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, token);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
