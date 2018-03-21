package zl.wang.cn.com.wangmyapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.first.FirstPagerFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.forth.ForthPagerFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.other.OtherPagerFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.second.SecondPagerFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.third.ThirdPagerFragment;

/**
 * Created by hahaha on 2018/3/7.
 */

public class BookPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public BookPagerAdapter(FragmentManager fm,String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return FirstPagerFragment.newInstance(mTitles[position]);
        }else if (position == 1){
            return SecondPagerFragment.newInstance(mTitles[position]);
        }else if (position == 2){
            return ThirdPagerFragment.newInstance(mTitles[position]);
        }else if (position == 3) {
            return ForthPagerFragment.newInstance(mTitles[position]);
        }
        return OtherPagerFragment.newInstance(mTitles[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
