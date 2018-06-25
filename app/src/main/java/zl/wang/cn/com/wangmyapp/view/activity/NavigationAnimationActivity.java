package zl.wang.cn.com.wangmyapp.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.activity.navigation.PageAdapter;
import zl.wang.cn.com.wangmyapp.view.activity.navigation.PageFragment;
import zl.wang.cn.com.wangmyapp.view.activity.navigation.ViewPagerHelper;

public class NavigationAnimationActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_navigation_animation);
        setUpView();
    }

    private void setUpView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageView ivWomen = (ImageView) findViewById(R.id.iv_woman);

        ArrayList<PageFragment> fragments = createFragments();
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pageAdapter);
        ViewPagerHelper.newInstance().startListener(viewPager, ivWomen,fragments);
    }

    private ArrayList<PageFragment> createFragments() {
        ArrayList<PageFragment> pageFragments = new ArrayList<>();
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_1));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_2));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_3));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_4));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_5));
        pageFragments.add(PageFragment.newInstance(R.layout.view_login));
        return pageFragments;
    }
}
