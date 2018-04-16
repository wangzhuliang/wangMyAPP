package zl.wang.cn.com.wangmyapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.bean.Water;
import zl.wang.cn.com.wangmyapp.custom_view.WaterView;

/**
 * Created by lcling on 2018/4/10.
 *
 */

public class WaterViewActivity extends AppCompatActivity {

    private WaterView mWaterView;

    private List<Water> mWaters = new ArrayList<>();{
        for (int i = 0; i <10; i++) {
            mWaters.add(new Water((int) (i + Math.random() * 4), "item" + i));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_view);

        mWaterView = findViewById(R.id.wv_water);
        mWaterView.setWaters(mWaters);
    }

    public void onRest(View view) {
        mWaterView.setWaters(mWaters);
    }
}
