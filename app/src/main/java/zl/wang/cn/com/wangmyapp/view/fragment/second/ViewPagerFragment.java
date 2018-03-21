package zl.wang.cn.com.wangmyapp.view.fragment.second;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.BookPagerAdapter;

/**
 * Created by hahaha on 2018/3/7.
 */

public class ViewPagerFragment extends SupportFragment {

    private TabLayout mTab;
    private ViewPager mViewPager;

    public static ViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_pager, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {

        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        /*mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());*/
        mViewPager.setAdapter(new BookPagerAdapter(getChildFragmentManager(),
                "first","second","third","forth","other"));

        mTab.setupWithViewPager(mViewPager);
    }
}
