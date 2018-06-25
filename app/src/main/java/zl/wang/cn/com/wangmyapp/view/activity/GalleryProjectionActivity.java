package zl.wang.cn.com.wangmyapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.utils.ImageAnotherUtil;
import zl.wang.cn.com.wangmyapp.view.activity.viewpager.MyTransformation;

public class GalleryProjectionActivity extends AppCompatActivity {

    private int pagerWidth;
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_projection);

        final ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        imageViewList=new ArrayList<>();
        ImageView first=new ImageView(GalleryProjectionActivity.this);

        /**
         * 为imageview生成的带犹豫倒影的bitmap
         */
        first.setImageBitmap(ImageAnotherUtil.getReverseBitmapById(R.mipmap.first,GalleryProjectionActivity.this));
        ImageView second=new ImageView(GalleryProjectionActivity.this);
        second.setImageBitmap(ImageAnotherUtil.getReverseBitmapById(R.mipmap.second,GalleryProjectionActivity.this));
        ImageView third=new ImageView(GalleryProjectionActivity.this);
        third.setImageBitmap(ImageAnotherUtil.getReverseBitmapById(R.mipmap.third,GalleryProjectionActivity.this));
        ImageView fourth=new ImageView(GalleryProjectionActivity.this);
        fourth.setImageBitmap(ImageAnotherUtil.getReverseBitmapById(R.mipmap.fourth,GalleryProjectionActivity.this));
        ImageView fifth=new ImageView(GalleryProjectionActivity.this);
        fifth.setImageBitmap(ImageAnotherUtil.getReverseBitmapById(R.mipmap.fifth,GalleryProjectionActivity.this));

        imageViewList.add(first);
        imageViewList.add(second);
        imageViewList.add(third);
        imageViewList.add(fourth);
        imageViewList.add(fifth);

        viewPager.setOffscreenPageLimit(3);
        pagerWidth= (int) (getResources().getDisplayMetrics().widthPixels*3.0f/5.0f);
        ViewGroup.LayoutParams lp=viewPager.getLayoutParams();
        if (lp==null){
            lp=new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        }else {
            lp.width=pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(-50);

        findViewById(R.id.activity_gallery_projection).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });

        viewPager.setPageTransformer(true,new MyTransformation());
        //viewPager.setPageTransformer(true,new DepthTransformation());
       // viewPager.setPageTransformer(true,new MyAnimation());
        //viewPager.setPageTransformer(true,new MyAnimation1());
        //viewPager.setPageTransformer(true,new ZoomTransformation());
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=imageViewList.get(position);
                container.addView(imageView,position);
                return imageView;
            }
        });
    }
}
