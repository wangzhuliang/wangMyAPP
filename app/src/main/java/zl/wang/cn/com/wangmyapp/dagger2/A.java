package zl.wang.cn.com.wangmyapp.dagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * 这个的局限性是我们不能给这个类中的多个构造器作@Inject注解。
 */
public class A {

    public String field;

    @Inject
    public A() {

    }

    public void doSomething() {
        Log.e("AModule","do something");
    }


}
