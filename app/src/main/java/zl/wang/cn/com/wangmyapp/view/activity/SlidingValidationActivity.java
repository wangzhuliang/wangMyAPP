package zl.wang.cn.com.wangmyapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.luozm.captcha.Captcha;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zl.wang.cn.com.wangmyapp.R;

/**
 * Created by hahaha on 2018/3/14.
 * 滑动验证
 */

public class SlidingValidationActivity extends AppCompatActivity {

    @BindView(R.id.captCha)
    Captcha captCha;
    @BindView(R.id.btn_mode)
    Button btnMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_validation);
        ButterKnife.bind(this);

        captCha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(SlidingValidationActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                return null;
            }

            @Override
            public String onFailed(int failCount) {
                Toast.makeText(SlidingValidationActivity.this,"验证失败,失败次数"+failCount,Toast.LENGTH_SHORT).show();
                return null;
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(SlidingValidationActivity.this,"验证超过次数，你的帐号被封锁",Toast.LENGTH_SHORT).show();
                return null;
            }
        });

    }

    @OnClick(R.id.btn_mode)
    public void onViewClicked() {
        if(captCha.getMode()==Captcha.MODE_BAR){
            captCha.setMode(Captcha.MODE_NONBAR);
            btnMode.setText("滑动条模式");
        }else{
            captCha.setMode(Captcha.MODE_BAR);
            btnMode.setText("无滑动条模式");
        }
    }
}
