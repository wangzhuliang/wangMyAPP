package zl.wang.cn.com.wangmyapp.dagger2;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @Provides  module类对外提供实例方法的注解
 *
 * component首先先去提供的module里面寻找提供的实例，
 * 没有找到时再去找构造函数
 *
 * 一个component可以依赖多个module，
 * 一个component可以依赖另一个component
 */
@Module
public class AModule {

    @Provides
    public A provideA(){
        return new A();
    }


}
