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

        ZXingLibrary.initDisplayOpinion(this);
        appContext = this;
        Utils.init(this);
    }
}
