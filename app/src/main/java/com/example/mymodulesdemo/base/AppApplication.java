package com.example.mymodulesdemo.base;

import com.example.libbase.base.SnBaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author ChenQiuE
 * Date：2019/3/5 17:14
 * Email：1077503420@qq.com
 */
public class AppApplication extends SnBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initLeaks();

    }

    /**
     * 初始化LeakCanary
     */
    private void initLeaks() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
