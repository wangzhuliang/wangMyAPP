package zl.wang.cn.com.wangmyapp.dagger2.another;

import dagger.Module;
import dagger.Provides;

/**
 * 新建一个UserModule来提供User的实例,
 * 提供实例方法使用自定义的UserScope注解，
 * 表示提供实例仅限于UserScope范围内使用。
 */

@Module
public class UserModule {

    private User userA;
    private User userB;

    public UserModule(User userA,User userB) {
        this.userB = userB;
        this.userA = userA;
    }


    @UserNamed("a")
    @Provides
    @UserScope
    User provideUserA(){
        return userA;
    }
    @UserNamed("b")
    @Provides
    @UserScope
    User provideUserB(){
        return userB;
    }



}
