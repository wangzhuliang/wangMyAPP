package zl.wang.cn.com.wangmyapp.base;

/**
 * Created by lcling on 2018/3/22.
 * 基类
 */

public interface BasePresenter {

    /**
     * presenter开始获取数据并调用view中方法改变界面显示，
     * 其调用时机是在Fragment类的onResume方法中。
     */
    void start();
}
