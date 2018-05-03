package zl.wang.cn.com.wangmyapp.dagger2.another;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 自定义UserScope注解
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {

}

