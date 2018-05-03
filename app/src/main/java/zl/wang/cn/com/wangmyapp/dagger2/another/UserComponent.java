package zl.wang.cn.com.wangmyapp.dagger2.another;

import dagger.Subcomponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.a.AComponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.a.AModule;
import zl.wang.cn.com.wangmyapp.dagger2.another.b.BComponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.b.BModule;

/**
 * 新建Component桥梁。
 * 他是一个子Component,依赖于一个全局的父Component。
 Subcomponent注解与Component依赖另一个Component有点像，
 他们区别在于：
 Subcomponent可以获取到父Component的所有可以产生出的对象。
 Component依赖则只能获取到被依赖的Component所暴露出来的可以生产的对象
 */
@UserScope
@Subcomponent(modules = UserModule.class)
public interface UserComponent {
    AComponent plus(AModule bModule);

    BComponent plus(BModule bModule);
}
