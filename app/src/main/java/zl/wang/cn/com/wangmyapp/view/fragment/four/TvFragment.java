package zl.wang.cn.com.wangmyapp.view.fragment.four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.fragment.four.child.TvFirstFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.MusicFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.child.MusicFirstFragment;

/**
 * Created by hahaha on 2018/3/1.
 * 仿最美有物
 */

public class TvFragment extends SupportFragment {


    public static SupportFragment newInstance(String a){
        return new TvFragment();
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
            loadRootFragment(R.id.fl_first_container, TvFirstFragment.newInstance());
        }
    }
}
