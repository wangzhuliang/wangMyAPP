package zl.wang.cn.com.wangmyapp.view.activity.wangrxjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.bean.BaseResponse;
import zl.wang.cn.com.wangmyapp.model.net.Api;
import zl.wang.cn.com.wangmyapp.model.net.RetrofitClient;


/**
 * Created by lcling on 2018/4/2.
 *
 */

public class WangRxjavaActivity extends RxAppCompatActivity {

    private static final String TAG = "WangRxjavaActivity";
    private Api api = RetrofitClient.get().create(Api.class);
    private TextView btn_operators;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocie);
        
        //WangDemo();
        /**
         * 网络请求
         */
        /*Api api = RetrofitProvider.get().create(Api.class);
        api.login(new LoginRequest())
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(LoginResponse value) {}

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(WangRxjavaActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(WangRxjavaActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });*/

        //优化网络请求
        //demo();
        //Zip操作符，从两个接口获取数据的时候
        //demo1();
        //Backpressure控制流量
        //demo2();
        //响应式拉
        //demo3();

        //实战
        //提到过如何利用Retrofit中的GsonConverter来处理API请求错误的方法，
        // ，今天给大家介绍另外一种优雅的方法，
        // 利用RxJava内部的RxJavaPlugins来做这么一个骚操作。
        //demo4();



        //封装
        iniViews();

    }

    private void iniViews() {

        btn_operators = (TextView) findViewById(R.id.btn_operators);


        btn_operators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WangRxjavaActivity.this,SelectionActivity.class);
                startActivity(intent);
            }
        });

    }




    private void WangDemo() {

        /**ObservableEmitter这个就是用来发出事件的，
         它可以发出三种类型的事件，
         通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)
         就可以分别发出next事件、complete事件和error事件*/
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: " + value);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();
                    // 切断
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        /**
         * 只关心onNext事件
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            //只相当于next
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "onNext: " + integer);
            }
        });


        /**
         * 让它去子线程中发送事件, 然后再改变下游的线程,
         * 让它去主线程接收事件
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };
        /**
         * 耗时操作在子线程
         * 就算指定多个，但只有第一次指定的有效
         */
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread()
                                .getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);

        /**Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         Schedulers.newThread() 代表一个常规的新线程
         AndroidSchedulers.mainThread() 代表Android的主线程*/


    }

    private void demo4() {
        requestSomeThing("http://www.wanandroid.com/tools/mockapi/4037/foo");
    }

    @SuppressLint("CheckResult")
    private void requestSomeThing(String url) {
        api.getSomeThing(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        Toast.makeText(WangRxjavaActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Something wrong", throwable);
                    }
                });
    }

    public static Subscription mSubscription;
    public static void request(long n) {
        mSubscription.request(n);
    }

    /*public static void main(String[] args) {
        practice1();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    private static void practice1() {
        //这个例子是读取一个文本文件，
        // 需要一行一行读取，然后处理并输出，如果文本文件很大的时候，
        // 比如几十M的时候，全部先读入内存肯定不是明智的做法，
        // 因此我们可以一边读取一边处理，实现的代码如下：
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                try {
                    FileReader reader = new FileReader("test.txt");
                    BufferedReader br = new BufferedReader(reader);

                    String str;

                    while ((str = br.readLine()) != null && !emitter.isCancelled()) {
                        while (emitter.requested() == 0) {
                            if (emitter.isCancelled()) {
                                break;
                            }
                        }
                        emitter.onNext(str);
                    }

                    br.close();
                    reader.close();

                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println(string);
                        try {
                            Thread.sleep(2000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void demo3() {

        //上下游在同一个线程中的时候，
        // 在下游调用request(n)就会直接改变上游中的requested的值，
        // 多次调用便会叠加这个值，而上游每发送一个事件之后便会去减少这个值，
        // 当这个值减少至0的时候，继续发送事件便会抛异常了
        Flowable.create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                        Log.d(TAG, "current requested: " + emitter.requested());
                    }
                }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        s.request(10);
                        s.request(100);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });


    }


    /**
     * 当上下游工作在同一个线程中时, 这时候是一个同步的订阅关系,
     * 也就是说上游每发送一个事件必须等到下游接收处理完了以后才能接着发送下一个事件.

     当上下游工作在不同的线程中时, 这时候是一个异步的订阅关系,
     这个时候上游发送数据不需要等待下游接收, 为什么呢, 因为两个线程并不能直接进行通信,
     因此上游发送的事件并不能直接到下游里去, 这个时候就需要一个田螺姑娘来帮助它们俩,
     这个田螺姑娘就是我们刚才说的水缸 ! 上游把事件发送到水缸里去,
     下游从水缸里取出事件来处理, 因此, 当上游发事件的速度太快,
     下游取事件的速度太慢, 水缸就会迅速装满, 然后溢出来, 最后就OOM了.

     一是从数量上进行治理, 减少发送进水缸里的事件
     subscribeOn(Schedulers.io()).sample(2, TimeUnit.SECONDS); //进行sample采样
     二是从速度上进行治理, 减缓事件发送进水缸的速度
     Thread.sleep(2000);  //发送事件之后延时2秒

     Flowable使用
     分别创建了一个上游Flowable和下游Subscriber, 上下游工作在同一个线程
     Flowable里默认有一个大小为128的水缸
     *
     */
    private void demo2() {

        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR);//增加了一个参数

        Subscriber<Integer> downstream = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);//注意这句代码
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        upstream.subscribe(downstream);

        /*D/TAG: onSubscribe
        D/TAG: emit 1
        D/TAG: onNext: 1
        D/TAG: emit 2
        D/TAG: onNext: 2
        D/TAG: emit 3
        D/TAG: onNext: 3
        D/TAG: emit complete
        D/TAG: onComplete*/

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    Log.d(TAG, "emit " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.MISSING).subscribeOn(Schedulers.io())
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

        //interval操作符发送Long型的事件, 从0开始, 每隔指定的时间就把数字加1并发送出来
        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext: " + aLong);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });


    }

    /**
     * Zip通过一个函数将多个Observable发送的事件结合到一起，
     * 然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。
     * 它只发射与发射数据项最少的那个Observable一样多的数据。
     */
    @SuppressLint("CheckResult")
    private void demo1() {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());//切换线程
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit AModule");
                emitter.onNext("AModule");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());//切换线程

        //这样一起运行的
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

        /*D/TAG: onSubscribe
        D/TAG: emit 1
        D/TAG: emit 2
        D/TAG: emit 3
        D/TAG: emit 4
        D/TAG: emit complete1
        D/TAG: emit AModule
        D/TAG: onNext: 1A
        D/TAG: emit B
        D/TAG: onNext: 2B
        D/TAG: emit C
        D/TAG: onNext: 3C
        D/TAG: emit complete2
        D/TAG: onComplete*/

        /**
         * Zip来打包请求:
         * UserInfo,为两个Bean
         */
        /*final Api api = RetrofitProvider.get().create(Api.class);
        Observable<UserBaseInfoResponse> observable11 =
                api.getUserBaseInfo(new UserBaseInfoRequest()).subscribeOn(Schedulers.io());

        Observable<UserExtraInfoResponse> observable22 =
                api.getUserExtraInfo(new UserExtraInfoRequest()).subscribeOn(Schedulers.io());

        Observable.zip(observable11, observable22,
                new BiFunction<UserBaseInfoResponse, UserExtraInfoResponse, UserInfo>() {
                    @Override
                    public UserInfo apply(UserBaseInfoResponse baseInfo,
                                          UserExtraInfoResponse extraInfo) throws Exception {
                        return new UserInfo(baseInfo, extraInfo);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        //do something;
                    }
                });*/

    }

    /**
     * 优化网络请求
     */
    @SuppressLint("CheckResult")
    private void demo() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
        /*D/TAG: This is result 1
        D/TAG: This is result 2
        D/TAG: This is result 3*/


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10,TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        /*D/TAG: I am value 1
        D/TAG: I am value 1
        D/TAG: I am value 1
        D/TAG: I am value 3
        D/TAG: I am value 3
        D/TAG: I am value 3
        D/TAG: I am value 2
        D/TAG: I am value 2
        D/TAG: I am value 2*/


        /**
         * concatMap 它和flatMap的作用几乎一模一样,
         * 只是它的结果是严格按照上游发送的顺序来发送的
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10,TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        /*D/TAG: I am value 1
        D/TAG: I am value 1
        D/TAG: I am value 1
        D/TAG: I am value 2
        D/TAG: I am value 2
        D/TAG: I am value 2
        D/TAG: I am value 3
        D/TAG: I am value 3
        D/TAG: I am value 3*/


        /**
         * 如何优雅的处理嵌套网络请求
         */
        /*final Api api = RetrofitProvider.get().create(Api.class);
        api.register(new RegisterRequest())            //发起注册请求
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        //先根据注册的响应结果去做一些操作
                        //.....
                    }
                })
                .observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        return api.login(new LoginRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        Toast.makeText(WangRxjavaActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(WangRxjavaActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

}
