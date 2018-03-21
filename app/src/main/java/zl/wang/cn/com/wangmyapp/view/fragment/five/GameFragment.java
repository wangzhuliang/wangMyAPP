package zl.wang.cn.com.wangmyapp.view.fragment.five;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.fragment.five.child.GameFirstFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.four.child.TvFirstFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.MusicFragment;

/**
 * Created by hahaha on 2018/3/1.
 */

public class GameFragment extends SupportFragment {


    public static SupportFragment newInstance(String a){
        return new GameFragment();
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

        if (findChildFragment(GameFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, GameFirstFragment.newInstance());
        }
    }
}
