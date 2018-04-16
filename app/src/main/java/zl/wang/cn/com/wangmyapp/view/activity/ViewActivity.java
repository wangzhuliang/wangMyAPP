package zl.wang.cn.com.wangmyapp.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zyyoona7.lib.EasyPopup;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.utils.Utils;

/**
 * Created by lcling on 2018/4/10.
 *
 */

public class ViewActivity extends AppCompatActivity {

    private LinearLayout ll_first;
    private ImageView iv_one;
    private EasyPopup easyPopup;
    private int fHeight;
    private int sHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ll_first = findViewById(R.id.ll_first);
        iv_one = findViewById(R.id.iv_one);

        easyPopup = new EasyPopup(this)
                .setContentView(R.layout.popup_dish_food)
                .setAnimationStyle(R.style.easyPopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
//                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
                //指定任意 ViewGroup 背景变暗
//                .setDimView(mTitleBar)
                .setHeight(Utils.dp2px(this,400))
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .createPopup();

        easyPopup.getView(R.id.tv_aaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyPopup.dismiss();
            }
        });

        easyPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                initHiddenAnim();
            }
        });

        ll_first.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                fHeight = ll_first.getHeight();
                ll_first.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        iv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShowAnim(v);
            }
        });
    }

    private void initShowAnim(final View view) {

        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(ll_first,"scaleX",1.0f,0.9f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(ll_first,"scaleY",1.0f,0.9f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(ll_first,"alpha",1.0f,0.5f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(ll_first, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(ll_first, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(ll_first,"translationY",0,-0.1f* fHeight);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(ll_first,"translationY",sHeight,0);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                easyPopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            }
        });
        AnimatorSet showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
        showAnim.start();
    }

    private void initHiddenAnim() {

        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(ll_first,"scaleX",0.9f,1.0f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(ll_first,"scaleY",0.9f,1.0f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(ll_first,"alpha",0.5f,1.0f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(ll_first, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(ll_first, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(ll_first,"translationY",-0.1f* fHeight,0);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(ll_first,"translationY",0,sHeight);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //second_view.setVisibility(View.INVISIBLE);
            }
        });
        AnimatorSet showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
        showAnim.start();
    }
}
