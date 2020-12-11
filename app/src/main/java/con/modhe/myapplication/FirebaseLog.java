package con.modhe.myapplication;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;


/**
 * 使用方法
 * 1.携带参数的打点
 * Bundle values = new Bundle();
 * values.putString("测试数据的key","测试数据的value");
 * values.putInt("测试整型数据的key",666);
 * FirebaseLog.getInstance().report("打点key名",values);
 * 2.不携带参数的打点
 * FirebaseLog.getInstance().report("不携带参数的打点key名");
 */


/**
 * author Created by harrishuang on 2020/11/11.
 * email : huangjinping1000@163.com
 */
public class FirebaseLog {
//    void logs(Context context) {
//        https://blog.csdn.net/AlpinistWang/article/details/87369279?utm_medium=distribute.pc_relevant.none-task-blog-title-2&spm=1001.2101.3001.4242
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.METHOD, "login");
//        FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
//
//        bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.METHOD, "login");
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//    }


    private static volatile FirebaseLog instance;
    private static FirebaseAnalytics mFirebaseAnalytics;

    public static FirebaseLog getInstance() {
        if (instance == null) {
            instance =new  FirebaseLog();
        }
        return instance;
    }

    private FirebaseLog() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(App.getContext());
    }

    /**
     * 埋点
     *
     * @param key
     * @param value
     */
    public void report(String key, Bundle value) {
        if (value == null) {
            value = new Bundle();
        }

        key = key.trim();

        mFirebaseAnalytics.logEvent(key, value);

    }

    /**
     * 埋点
     *
     * @param key
     */
    public void report(String key) {
        report(key, null);
    }


}
