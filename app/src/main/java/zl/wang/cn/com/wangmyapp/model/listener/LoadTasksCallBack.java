package zl.wang.cn.com.wangmyapp.model.listener;

/**
 * Created by 99142 on 2018/2/28.
 * 回调监听接口
 */

public interface LoadTasksCallBack<T> {

    void onSuccess(T t);
    void onStart();
    void onFailed();
    void onFinish();


}
