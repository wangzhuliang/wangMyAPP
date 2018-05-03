package zl.wang.cn.com.wangmyapp.dagger2.base;

import javax.inject.Singleton;

import dagger.Component;
import zl.wang.cn.com.wangmyapp.dagger2.AComponent;
import zl.wang.cn.com.wangmyapp.dagger2.AModule;
import zl.wang.cn.com.wangmyapp.dagger2.GsonModule;
import zl.wang.cn.com.wangmyapp.dagger2.another.UserComponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.UserModule;

/**
 * 新建一个全局的父Component，引用子Component。
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    UserComponent plus(UserModule userModule);
}
