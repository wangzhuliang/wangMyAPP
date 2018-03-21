package zl.wang.cn.com.wangmyapp.view.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.base.BaseMainFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.first.child.HomeFirstFragment;

/**
 * Created by hahaha on 2018/3/1.
 * homefragment
 */

public class HomeFragment extends BaseMainFragment{

    public static SupportFragment newInstance(String a){

        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);

        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        return root;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, HomeFirstFragment.newInstance());
        }
    }
}
