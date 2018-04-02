package zl.wang.cn.com.wangmyapp.base;

/**
 * Created by 99142 on 2018/2/28.
 * baseview
 */

public interface BaseView<T> {

    /**将presenter实例传入view中，
     * 其调用时机是presenter实现类的构造函数中。*/
    void setPresenter(T presenter);
}
