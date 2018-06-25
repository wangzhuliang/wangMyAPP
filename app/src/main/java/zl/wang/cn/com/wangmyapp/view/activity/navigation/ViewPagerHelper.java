package zl.wang.cn.com.wangmyapp.view.activity.navigation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/8/25 下午6:12
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/8/25      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class ViewPagerHelper implements ViewPager.OnPageChangeListener {

    private ImageView ivWomen;//在 view 滑动的时候走路的女人
    private ArrayList<ParallaxViewImp> mViews = new ArrayList<>();//所有实现了ParallaxViewImp接口的 Fragment
    private int mWidth;//ViewPager 的宽度


    private ViewPagerHelper() {
    }

    public static ViewPagerHelper newInstance() {
        return new ViewPagerHelper();
    }

    public void startListener(ViewPager viewPager, ImageView ivWomen, ArrayList<PageFragment> fragments) {
        this.ivWomen = ivWomen;
        viewPager.setOnPageChangeListener(this);
        mWidth = viewPager.getWidth();
        mViews.addAll(fragments);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //在翻页的过程中，不断根据视图的标签中对应的动画参数，改变视图的位置或者透明度
        //获取到进入的页面
        ParallaxViewImp inFragment = getPosition(position - 1);

        //获取到退出的页面
        ParallaxViewImp outFragment = getPosition(position);

        if (inFragment != null) {
            //获取Fragment上所有的视图，实现动画效果
            List<View> inViews = inFragment.getParallaxViews();
            if (inViews != null) {
                for (View view : inViews) {
                    //获取标签，从标签上获取所有的动画参数
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(view.getId());
                    if (tag == null) {
                        continue;
                    }
                    //translationY改变view的偏移位置，translationY=100，代表view在其原始位置向下移动100
                    //仔细观察进入的fragment中view从远处过来，不断向下移动，最终停在原始位置
                    view.setTranslationY((mWidth - positionOffsetPixels) * tag.yIn);
                    view.setTranslationX((mWidth - positionOffsetPixels) * tag.xIn);
                }
            }
        }

        if (outFragment != null) {
            List<View> outViews = outFragment.getParallaxViews();
            if (outViews != null) {
                for (View view : outViews) {
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(view.getId());
                    if (tag == null) {
                        continue;
                    }
                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    view.setTranslationX(0 - positionOffsetPixels * tag.xOut);
                    view.setTranslationY(0 - positionOffsetPixels * tag.yOut);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == mViews.size() - 1) {
            ivWomen.setVisibility(View.INVISIBLE);
        } else {
            ivWomen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Drawable background = ivWomen.getBackground();
        if (!(background instanceof AnimationDrawable))
            return;
        AnimationDrawable animation = (AnimationDrawable) background;
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animation.start();
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                animation.stop();
                break;
            default:
                break;
        }
    }


    private ParallaxViewImp getPosition(int position) {
        ParallaxViewImp parallaxViewImp = null;
        if (position >= 0 && position < mViews.size())
            parallaxViewImp = mViews.get(position);
        return parallaxViewImp;
    }
}
