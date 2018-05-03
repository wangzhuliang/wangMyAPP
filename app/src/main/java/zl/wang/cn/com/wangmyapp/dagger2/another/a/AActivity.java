package zl.wang.cn.com.wangmyapp.dagger2.another.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.base.MyApplication;
import zl.wang.cn.com.wangmyapp.dagger2.another.User;
import zl.wang.cn.com.wangmyapp.dagger2.another.UserNamed;
import zl.wang.cn.com.wangmyapp.dagger2.another.b.BActivity;

/**
 * Created by wanglj on 16/6/22.
 */

public class AActivity extends AppCompatActivity{

    @BindView(R.id.tv_all)
    TextView tv_all;

    @UserNamed("a")
    @Inject
    User userA;

    @UserNamed("b")
    @Inject
    User userB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);
        //注入
        MyApplication.get(this).getUserComponent().plus(new AModule()).inject(this);

        Log.d("aa","username:"+userA.getName()+"--user:"+userA+""+"username:"+userB.getName()+"--user:"+userB);

        tv_all.setText("username:"+userA.getName()+"--user:"+userA+""+"username:"+userB.getName()+"--user:"+userB);

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AActivity.this, BActivity.class));
            }
        });
    }

}
