package zl.wang.cn.com.wangmyapp.model.net;

import zl.wang.cn.com.wangmyapp.model.listener.LoadTasksCallBack;

/**
 * Created by 99142 on 2018/2/28.
 * 定义获取网络数据接口类
 */

public interface NetTask<T> {

    void execute(T data, LoadTasksCallBack callBack);
}
