package zl.wang.cn.com.wangmyapp;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import java.util.ArrayList;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.base.BaseMainFragment;
import zl.wang.cn.com.wangmyapp.event.TabSelectedEvent;
import zl.wang.cn.com.wangmyapp.utils.NetworkUtil;
import zl.wang.cn.com.wangmyapp.utils.ToastUtils;
import zl.wang.cn.com.wangmyapp.view.fragment.second.BookFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.five.GameFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.first.HomeFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.MusicFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.four.TvFragment;

public class MainActivity extends SupportActivity implements BottomNavigationBar.OnTabSelectedListener,BaseMainFragment.OnBackToFirstListener{

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;
    private SupportFragment[] mFragments = new SupportFragment[5];

    private Handler mhandler = new  Handler(){
        // 通过复写handlerMessage()从而确定更新UI的操作
        @Override
        public void handleMessage(Message msg) {
                // 需执行的UI操作

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!NetworkUtil.isNetWorkAvailable(this)){
            ToastUtils.showLongToast("无网络");
        }

        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        //设置导航栏模式
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //设置导航栏背景模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //BACKGROUND_STYLE_RIPPLE
        //BACKGROUND_STYLE_STATIC,加上这个字体颜色可以修改
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem();

        //添加标记
        numberBadgeItem.setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("1" )
                .setHideOnSelect(true);

        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.yellow)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(true);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.iv_assess_cut,"Home")
                .setActiveColorResource(R.color.purple))
                //.setInActiveColorResource(R.color.deep_gray)
                .addItem(new BottomNavigationItem(R.mipmap.iv_assess_fridge,"Books").setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.mipmap.iv_assess_modification,"Music").setActiveColorResource(R.color.light_blue))
                .addItem(new BottomNavigationItem(R.mipmap.iv_assess_record,"Movies")
                        .setActiveColorResource(R.color.light_yellow).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.iv_add,"Games")
                        .setActiveColorResource(R.color.orange).setBadgeItem(shapeBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise();

        //MODE_FIXED+BACKGROUND_STYLE_STATIC效果
        //MODE_FIXED+BACKGROUND_STYLE_RIPPLE效果
        //MODE_SHIFTING+BACKGROUND_STYLE_STATIC效果
        //MODE_SHIFTING+BACKGROUND_STYLE_RIPPLE效果

        //fragments = getFragments();
        //setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance("Home");
            mFragments[SECOND] = BookFragment.newInstance("Books");
            mFragments[THIRD] = MusicFragment.newInstance("Music");
            mFragments[FOURTH] = TvFragment.newInstance("Movies");
            mFragments[FIVE] = GameFragment.newInstance("Games");

            loadMultipleRootFragment(R.id.layFrame, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIVE]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(BookFragment.class);
            mFragments[THIRD] = findFragment(MusicFragment.class);
            mFragments[FOURTH] = findFragment(TvFragment.class);
            mFragments[FIVE] = findFragment(GameFragment.class);
        }

    }

    @Override
    public void onTabSelected(int position) {
        showHideFragment(mFragments[position]);
        /*if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }*/
    }

    @Override
    public void onTabUnselected(int position) {
        /*if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }*/
    }

    @Override
    public void onTabReselected(int position) {
        final SupportFragment currentFragment = mFragments[position];
        int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

        // 如果不在该类别Fragment的主页,则回到主页;
        if (count > 1) {
            if (currentFragment instanceof HomeFragment) {
                currentFragment.popToChild(HomeFragment.class, false);
            } else if (currentFragment instanceof BookFragment) {
                currentFragment.popToChild(BookFragment.class, false);
            } else if (currentFragment instanceof MusicFragment) {
                currentFragment.popToChild(MusicFragment.class, false);
            } else if (currentFragment instanceof TvFragment) {
                currentFragment.popToChild(TvFragment.class, false);
            } else if (currentFragment instanceof GameFragment) {
                currentFragment.popToChild(GameFragment.class, false);
            }
            return;
        }


        // 这里推荐使用EventBus来实现 -> 解耦
        if (count == 1) {
            // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
            // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
            EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
        }
    }

    /** * 设置默认的 */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("Home"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(BookFragment.newInstance("Books"));
        fragments.add(MusicFragment.newInstance("Music"));
        fragments.add(TvFragment.newInstance("Movies"));
        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        //bottomNavigationBar.setCurrentItem(0);
    }
}
