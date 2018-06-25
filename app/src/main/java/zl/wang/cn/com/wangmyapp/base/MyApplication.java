package zl.wang.cn.com.wangmyapp.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.xutils.x;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.plugins.RxJavaPlugins;
import zl.wang.cn.com.wangmyapp.bean.BaseResponse;
import zl.wang.cn.com.wangmyapp.dagger2.another.User;
import zl.wang.cn.com.wangmyapp.dagger2.another.UserComponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.UserModule;
import zl.wang.cn.com.wangmyapp.dagger2.base.AppComponent;
import zl.wang.cn.com.wangmyapp.dagger2.base.AppModule;
import zl.wang.cn.com.wangmyapp.dagger2.base.DaggerAppComponent;
import zl.wang.cn.com.wangmyapp.utils.Exceptions;
import zl.wang.cn.com.wangmyapp.utils.Utils;
import zl.wang.cn.com.wangmyapp.view.activity.ViewActivity;

/**
 * Created by hahaha on 2018/3/9.
 * application
 */

public class MyApplication extends Application {

    private static MyApplication appContext;
    public static MyApplication getInstance() {
        return appContext;
    }
    private static final String TAG = "CustomApplication";
    private Handler mainHandler;

    //application组件
    private AppComponent appComponent;
    //用户组件
    private UserComponent userComponent;
    //获取当前application的实例
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        Utils.init(this);

        initThirdService();

        mainHandler = new Handler(getMainLooper());

        RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws Exception {
                return new ObservableSubscribeHooker(observer);
            }
        });

        //注入全局Application
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        x.Ext.init(this);
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

    //对外提供UserComponent
    public UserComponent getUserComponent() {
        return userComponent;
    }

    //注入UserComponent，调用此方法后，UserCope生效
    public UserComponent createUserComponent(User userA,User userB) {
        userComponent = appComponent.plus(new UserModule(userA,userB));
        return userComponent;
    }
    //释放UserComponent组件
    public void releaseUserComponent() {
        userComponent = null;
    }


    class ObservableSubscribeHooker<T> implements Observer<T> {
        private Observer<T> actual;

        public ObservableSubscribeHooker(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onSubscribe(Disposable d) {
            actual.onSubscribe(d);
        }

        @Override
        public void onNext(T t) {
            hookOnNext(t);
            actual.onNext(t);
        }

        private void hookOnNext(T t) {
            if (t instanceof BaseResponse) {
                BaseResponse baseResponse = (BaseResponse) t;
                if (baseResponse.getCode() == 100) {

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "登录过期", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    throw new Exceptions.TokenExpired();
                }
            }
        }


        @Override
        public void onError(Throwable e) {

            if (e instanceof ConnectException) {
                Log.e(TAG, "Connect failed: ", e);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();
                    }
                });
                actual.onError(new Exceptions.Offline());
                return;
            }

            if (e instanceof SocketTimeoutException) {
                Log.e(TAG, "Time out ", e);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "连接超时", Toast.LENGTH_SHORT).show();
                    }
                });
                actual.onError(new Exceptions.TimeOut());
                return;
            }

            //其余的异常处理...

            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
