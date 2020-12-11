package con.modhe.myapplication.utils;

/**
 * author Created by harrishuang on 2020/12/9.
 * email : huangjinping1000@163.com
 */

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * author Created by harrishuang on 2020/12/9.
 * email : huangjinping1000@163.com
 */
public class FCMHelper {


    public void getFirebaseMessaging() {
        final String TAG = "Messaging";

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d(TAG, "  " + token);
                    }
                });
    }


}
