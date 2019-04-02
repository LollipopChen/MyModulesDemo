package com.example.libbase.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.example.libbase.widget.toast.ToastAlert;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.components.DefaultOnCompleteListener;
import com.sankuai.waimai.router.core.Debugger;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/1 14:02
 * Email：1077503420@qq.com
 */
public class SnBaseApplication extends Application {

    protected static String TAG;

    /**
     * The number of activities in the application.
     * If the number is 0, you can determine that the current application is not started
     */
    private static int mActivityCount = 0;
    private static List<Activity> activities = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getClass().getSimpleName();
        this.registerActivityLifecycleCallbacks(new SnFrameworkActivityLifeCycleCallback());

        setupLogger();
        ToastAlert.init(this);

        initRouter();
    }

    /**
     * 初始化路由
     */
    @SuppressLint("StaticFieldLeak")
    private void initRouter() {

        // Log开关，建议测试环境下开启，方便排查问题。
        Debugger.setEnableLog(true);

        // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
        Debugger.setEnableDebug(true);

        // 创建RootHandler
        DefaultRootUriHandler rootHandler = new DefaultRootUriHandler(this);

        // 设置全局跳转完成监听器，可用于跳转失败时统一弹Toast提示，做埋点统计等。
        rootHandler.setGlobalOnCompleteListener(DefaultOnCompleteListener.INSTANCE);

        // 初始化
        Router.init(rootHandler);

        // 懒加载后台初始化（可选）
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Router.lazyInit();
                return null;
            }
        }.execute();
    }

    /**
     * 当前是否为DeBug版本
     * @return boolean
     */
    private boolean isDebug() {
        return true;
    }

    /**
     * 初始化log
     */
    private void setupLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag(TAG).build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    /**
     * Get AppContext by reflection, avoid using static context directly
     * 用到了反射，非不得已尽量少用
     */
    @SuppressLint("PrivateApi")
    public static Context getAppContext() {
        Application application = null;
        try {
            application = (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return application;
    }

    /**
     * App名
     * The framework layer cannot directly obtain the String resource of the application layer,
     * so it can be obtained by this method when the framework needs the application name.
     */
    public static String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /**
     * AppIcon
     * Get the drawable resource of application icon.
     */
    @Nullable
    public static Drawable getApplicationIcon() {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getAppContext().getPackageName(), 0);
            return applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * App版本名
     * Get application version name.
     */
    @Nullable
    public static String getApplicationVersionName() {
        PackageManager packageManager;
        PackageInfo packageInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * App版本号
     * Get application version code.
     */
    public static int getApplicationVersionCode() {
        PackageManager packageManager;
        PackageInfo packageInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get application signature
     */
    @Nullable
    public static String getAppSignature() {
        PackageManager packageManager;
        PackageInfo packageInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signature = packageInfo.signatures[0];

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
            String pubKey = cert.getPublicKey().toString();
            String signNumber = cert.getSerialNumber().toString();
            return pubKey + "|" + signNumber;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Logger.d(TAG, "package name not found");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check if the application is in the foreground
     */
    public static boolean isAppForeground() {
        Context context = getAppContext();
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (!appProcess.processName.equals(packageName)) {
                continue;
            }
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chcek if system screen rotation is turned on.
     */
    public static boolean isSystemOrientationEnabled() {
        return Settings.System.getInt(getAppContext().getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
    }

    /**
     * Get system boot time.
     */
    public static long getSystemBootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    /**
     * Get the number of running Activities.
     */
    public static int getRunningActivityCount() {
        return mActivityCount;
    }

    /**
     * Exit the application by a broadcast.
     */
    public static void exitApplication() {
        Intent exitIntent = new Intent();
        exitIntent.setAction(BaseActivity.ACTION_EXIT_APPLICATION);
        SnBaseApplication.getAppContext().sendBroadcast(exitIntent);
    }

    /**
     * 添加
     *
     * @param activity 参数
     */
    private void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 删除
     *
     * @param activity 参数
     */
    private void deleteActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    private class SnFrameworkActivityLifeCycleCallback implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mActivityCount++;
            addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mActivityCount--;
            deleteActivity(activity);
        }
    }
}
