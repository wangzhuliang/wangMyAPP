package zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.other;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;

/**
 * Created by hahaha on 2018/3/7.
 */

public class OtherPagerFragment extends SupportFragment {

    private static final String ARG_TYPE = "arg_type";

    private String mTitle;

    public static OtherPagerFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE, title);
        OtherPagerFragment fragment = new OtherPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_pager_other, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);


        tvTitle.setText(mTitle+"...");

        /**
         * 加载json动画
         */
        animationView.setAnimation("test.json");//在assets目录下的动画json文件名。
        animationView.loop(true);//设置动画循环播放
        animationView.setImageAssetsFolder("testimages/");//assets目录下的子目录，存放动画所需的图片
        //animationView.setProgress(1f);
        animationView.playAnimation();//播放动画

        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Do something.动画状态监听回调
            }
        });

        //progress范围0~1f，设置动画进度
        //animationView.setProgress(0.5f);

        //自定义动画时长，此处利用ValueAnimator值动画来实时更新AnimationView的进度来达到控制动画时长。
        //ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500);

        //animator.start();//启动动画

        //animationView.cancelAnimation();//取消动画
    }
}
