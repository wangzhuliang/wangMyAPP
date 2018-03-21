package zl.wang.cn.com.wangmyapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import butterknife.ButterKnife;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.custom_view.SmileView;

/**
 * Created by hahaha on 2018/3/14.
 * 点赞效果
 */

public class LikeActivity extends AppCompatActivity {


    SeekBar seekBar;
    LinearLayout backGround;
    ImageView smileFace;

    SmileView smileView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        backGround = (LinearLayout) findViewById(R.id.backGround);
        smileFace = (ImageView) findViewById(R.id.smileFace);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) smileFace.getLayoutParams();
                layoutParams.bottomMargin = i * 3;
                smileFace.setLayoutParams(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        smileView = (SmileView) findViewById(R.id.smileView);
        smileView.setNum(60,40);
    }


}
