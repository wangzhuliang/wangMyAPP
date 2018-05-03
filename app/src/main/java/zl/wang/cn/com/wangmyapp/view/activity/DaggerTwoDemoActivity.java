package zl.wang.cn.com.wangmyapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zl.wang.cn.com.wangmyapp.MainActivity;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.base.MyApplication;
import zl.wang.cn.com.wangmyapp.dagger2.A;
import zl.wang.cn.com.wangmyapp.dagger2.AComponent;
import zl.wang.cn.com.wangmyapp.dagger2.AModule;
import zl.wang.cn.com.wangmyapp.dagger2.DaggerAComponent;
import zl.wang.cn.com.wangmyapp.dagger2.another.User;
import zl.wang.cn.com.wangmyapp.dagger2.another.a.AActivity;

/**
 * 解决项目中多实例依赖创建问题，如：new AModule(new B(new C()))。
 *
 更好的对象生命周期依赖和管理，如通过@Scope规范实例的生命周期。

 规范代码，提高解耦能力，增强代码的拓展能力，如类的依赖、创建、复用、
 拓展都通过@Component、@Module、@Inject的规范实现。

 * @Inject 指定需要注入的类和环境
 *
 * @Module 提供注入所需的依赖
 * 在内部使用 @Provides注解的以provide开头的方法
 *
 * @Component Component 就是将 @Inject 和 @Module 联系起来的桥梁，
 * 从 @Module 中获取依赖，并将依赖注入给 @Inject 注解的参数。
 *
 *

 */
public class DaggerTwoDemoActivity extends AppCompatActivity {

    @BindView(R.id.textview)
    TextView textView;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.button)
    Button button;


    /*@Inject
    A a;

    @Inject
    Gson gson;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_two);
        ButterKnife.bind(this);

        //DaggerAComponent.builder().build().inject(this);

        /*a.doSomething();
        a.field = "text";
        String s = gson.toJson(a);
        Log.e("mainactivity","s = "+s);*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userA = new User();
                userA.setName(username.getText().toString()+"AAA");
                userA.setAvatar(password.getText().toString()+"aaa");

                User userB = new User();
                userB.setName(username.getText().toString()+"BBB");
                userB.setAvatar(password.getText().toString()+"bbb");

                MyApplication.get(DaggerTwoDemoActivity.this).createUserComponent(userA,userB);
                startActivity(new Intent(DaggerTwoDemoActivity.this, AActivity.class));
            }
        });


    }


}
