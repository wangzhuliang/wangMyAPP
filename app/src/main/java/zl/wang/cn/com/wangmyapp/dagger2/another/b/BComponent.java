package zl.wang.cn.com.wangmyapp.dagger2.another.b;

import dagger.Subcomponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.a.AActivity;

/**
 * Created by wanglj on 16/6/22.
 */
@Subcomponent(modules = BModule.class)
public interface BComponent {
    void inject(BActivity bActivity);
}
