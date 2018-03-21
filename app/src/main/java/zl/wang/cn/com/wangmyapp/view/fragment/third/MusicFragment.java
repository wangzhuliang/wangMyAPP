package zl.wang.cn.com.wangmyapp.view.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.fragment.first.HomeFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.first.child.HomeFirstFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.child.MusicFirstFragment;

/**
 * Created by hahaha on 2018/3/1.
 */

public class MusicFragment extends SupportFragment {


    public static SupportFragment newInstance(String a){
        return new MusicFragment();
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

        if (findChildFragment(MusicFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, MusicFirstFragment.newInstance());
        }
    }


}
