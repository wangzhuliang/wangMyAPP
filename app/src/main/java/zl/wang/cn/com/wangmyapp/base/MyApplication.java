package zl.wang.cn.com.wangmyapp.base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import zl.wang.cn.com.wangmyapp.utils.Utils;

/**
 * Created by hahaha on 2018/3/9.
 * application
 */

public class MyApplication extends Application {

    private static MyApplication appContext;
    public static MyApplication getInstance() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        Utils.init(this);

        initThirdService();
    }

    /**
     * 初始化一些三方服务
     */
    public static void initThirdService() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //设置线程的优先级；不与主线程抢资源
                ZXingLibrary.initDisplayOpinion(appContext);
            }
        }.start();
    }
}
