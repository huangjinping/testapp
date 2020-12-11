package con.modhe.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.appevents.AppEventsConstants;

/**
 * author Created by harrishuang on 2020/11/14.
 * email : huangjinping1000@163.com
 */
public class FBLogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FaceBookLog.getInstance().report(AppEventsConstants.EVENT_NAME_SCHEDULE);
        FirebaseLog.getInstance().report("FBLogActivity");
    }


    public void onClickView(View view) {

        FirebaseLog.getInstance().report("FBLogActivityonClickView");

        FaceBookLog.getInstance().report(AppEventsConstants.EVENT_NAME_CONTACT);
        FaceBookLog.getInstance().report(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT);
        FaceBookLog.getInstance().report(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT);

        Log.d("okhttp", "view");
    }
}
