package zl.wang.cn.com.wangmyapp.custom_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mrmo.mnavbarviewlib.MNavBarPopupSortView;
import com.mrmo.mnavbarviewlib.NavBarSortModel;
import com.mrmo.mnavbarviewlib.adapter.MNavBarSortAdapter;
import com.mrmo.mnavbarviewlib.impl.INavBarPopupView;
import com.mrmo.mnavbarviewlib.impl.NavBarPopupSelectListener;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;

public class wangview extends View implements INavBarPopupView {

    private NavBarPopupSelectListener navBarPopupSelectListener;
    private int navBarPopupViewHeight = 210;

    private Context context;

    public wangview(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public wangview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public wangview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        View view = View.inflate(context, R.layout.activity_like,null);

        view.findViewById(R.id.rl_aaa).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wangview.this.navBarPopupSelectListener != null) {
                    wangview.this.navBarPopupSelectListener.onSelect(wangview.this, 1, "a");
                }
            }
        });
    }

    @Override
    public void setNavBarPopupViewHeight(int i) {
        this.navBarPopupViewHeight = i;
    }

    @Override
    public int getNavBarPopupViewHeight() {
        return this.navBarPopupViewHeight;
    }

    @Override
    public void setOnNavBarPopupSelectListener(NavBarPopupSelectListener navBarPopupSelectListener) {
        this.navBarPopupSelectListener = navBarPopupSelectListener;
    }
}
