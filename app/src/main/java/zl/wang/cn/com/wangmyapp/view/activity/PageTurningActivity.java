package zl.wang.cn.com.wangmyapp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.custom_view.Marquee3DView;

public class PageTurningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_turning);

        final Marquee3DView marquee3DView = findViewById(R.id.marquee3DView);
        final Marquee3DView marquee3DView2 = findViewById(R.id.marquee3DView2);
        final List<String> labels = new ArrayList<>();
        labels.add("尾号1111抽到一份大礼");
        labels.add("尾号2222获得优惠券一张");
        labels.add("小美用户获得一张旅游机票");
        labels.add("新款昂克赛拉今年中旬上市");
        final List<String> labels1 = new ArrayList<>();
        labels1.add("尾号1111抽到一份大礼,");
        labels1.add("尾号2222获得优惠券一张.");
        labels1.add("小美用户获得一张旅游机票,");
        labels1.add("新款昂克赛拉今年中旬上市.");
        marquee3DView.setMarqueeLabels(labels);
        List<Bitmap> labelBitmapList = new ArrayList<>();
        labelBitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.timg));
        labelBitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.default_avatar_img));
        labelBitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.single));
        labelBitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.timg));
        labelBitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.default_avatar_img));
        marquee3DView2.setLabelBitmap(labelBitmapList);
        marquee3DView.setOnWhereItemClick(new Marquee3DView.OnWhereItemClick() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(PageTurningActivity.this, labels.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        marquee3DView2.setMarqueeLabels(labels1);
        marquee3DView2.setOnWhereItemClick(new Marquee3DView.OnWhereItemClick() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(PageTurningActivity.this, labels1.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
