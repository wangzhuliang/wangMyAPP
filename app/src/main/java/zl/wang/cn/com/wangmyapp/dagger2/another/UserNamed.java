package zl.wang.cn.com.wangmyapp.dagger2.another;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Qualifier;

/**
 * Created by wanglj on 16/6/23.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNamed {
    String value() default "";
}
