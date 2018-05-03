package zl.wang.cn.com.wangmyapp.dagger2;

import dagger.Component;
import zl.wang.cn.com.wangmyapp.view.activity.DaggerTwoDemoActivity;

/**
 * 关联
 * component类 引用module为AModule.class
 */

@Component(modules = {AModule.class,GsonModule.class})
public interface AComponent {

    void inject(DaggerTwoDemoActivity daggerTwoDemoActivity);
}
