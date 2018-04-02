package zl.wang.cn.com.wangmyapp.custom_view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import zl.wang.cn.com.wangmyapp.view.fragment.third.child.MusicFirstFragment;

/**
 * Created by lcling on 2018/3/29.
 *
 */

public class WangLinearLayoutManager extends LinearLayoutManager {

    private DyListener dyListener;
    private float MILLISECONDS_PER_INCH = 0.5f;

    public WangLinearLayoutManager(Context context, MusicFirstFragment musicFirstFragment) {
        super(context);
        dyListener = musicFirstFragment;
    }

    public WangLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WangLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //return super.scrollVerticallyBy(dy, recycler, state);
        int a = super.scrollVerticallyBy(dy, recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
        if (a < -120){
            dyListener.toTop();
            return 0;
        }
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        //super.smoothScrollToPosition(recyclerView, state, position);
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return WangLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            //控制速度。
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics);
               // return MILLISECONDS_PER_INCH / displayMetrics.density;
            }
            /*@Override
            protected int calculateTimeForScrolling(int dx) {
                if (dx > 3000) {
                    dx = 3000;
                }
                int time = super.calculateTimeForScrolling(dx);
                return time;
            }*/
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public interface DyListener {
        void toTop();
    }
}
