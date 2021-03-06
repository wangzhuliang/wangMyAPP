package zl.wang.cn.com.wangmyapp.view.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mrmo.mnavbarviewlib.MNavBarItemTitleView;
import com.mrmo.mnavbarviewlib.MNavBarPopupSortView;
import com.mrmo.mnavbarviewlib.MNavBarView;
import com.mrmo.mnavbarviewlib.NavBarSortModel;
import com.mrmo.mnavbarviewlib.adapter.MNavBarSortAdapter;
import com.mrmo.mnavbarviewlib.impl.INavBarItemView;
import com.mrmo.mnavbarviewlib.impl.NavBarPopupSelectListener;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;

public class PullDownMenuAnotherActivity extends AppCompatActivity {


    private ListView listView;
    private List list;
    private MNavBarSortAdapter adapter;
    private MNavBarView mNavBarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down_menu_another);

        initListView();
        initNavBarView();
    }

    private void initNavBarView() {
        mNavBarView = (MNavBarView) findViewById(R.id.mNavBarView);

        INavBarItemView itemViewAdress = new MNavBarItemTitleView(this);
        itemViewAdress.setTitle("地区");
//        itemViewAdress.setTitleColorSelect(Color.RED);

        INavBarItemView itemViewTime = new MNavBarItemTitleView(this);
        itemViewTime.setTitle("时间段");
//        itemViewTime.setTitleColorSelect(Color.RED);

        //INavBarItemView itemViewFilter = new MNavBarItemTitleView(this);
        //itemViewFilter.setTitle("筛选");
//        itemViewFilter.setTitleColorSelect(Color.RED);

        List list = new ArrayList();
        list.add(itemViewAdress);
        list.add(itemViewTime);

        //list.add(itemViewFilter);

        mNavBarView.setNavBarItemView(list);
        mNavBarView.setNavBarViewBGColor(Color.WHITE);

        MNavBarPopupSortView sortView = new MNavBarPopupSortView(this);
        sortView.setBackgroundColor(Color.RED);
        sortView.setNavBarPopupViewHeight(250);
        sortView.setOnNavBarPopupSelectListener(new NavBarPopupSelectListener() {
            @Override
            public void onSelect(View view, int index, Object itemData) {
                mNavBarView.hide();
                mNavBarView.isShowNavBarItemIcon(false, index);
                NavBarSortModel model = (NavBarSortModel) itemData;
//                mdNavBarView.setNavBarItemTitle(model.getTitle(), index);
            }
        });

        MNavBarPopupSortView sortView1 = new MNavBarPopupSortView(this);
        View view = View.inflate(this,R.layout.activity_like,null);
        sortView1.addView(view,0);
        //sortView1.setBackgroundColor(Color.YELLOW);
        sortView1.setNavBarPopupViewHeight(350);//设置下拉菜单的高度
        sortView1.setOnNavBarPopupSelectListener(new NavBarPopupSelectListener() {
            @Override
            public void onSelect(View view, int index, Object itemData) {
                mNavBarView.hide();
                mNavBarView.isShowNavBarItemIcon(false, index);
                NavBarSortModel model = (NavBarSortModel) itemData;
                mNavBarView.setNavBarItemTitle(model.getTitle(), index);//更新导航标题
            }
        });



        /*MNavBarPopupSortView sortView2 = new MNavBarPopupSortView(this);
        sortView2.setBackgroundColor(Color.BLUE);
        sortView2.setNavBarPopupViewHeight(450);
        sortView2.setOnNavBarPopupSelectListener(new NavBarPopupSelectListener() {
            @Override
            public void onSelect(View view, int index, Object itemData) {
                mNavBarView.hide();
                mNavBarView.isShowNavBarItemIcon(false, index);
                NavBarSortModel model = (NavBarSortModel) itemData;
//                mdNavBarView.setNavBarItemTitle(model.getTitle(), index);
            }
        });*/

        List listOperateView = new ArrayList();
        listOperateView.add(sortView);
        listOperateView.add(sortView1);
        //listOperateView.add(sortView2);
        mNavBarView.setNavBarPopupOperateView(listOperateView);
    }

    private void initListView() {
        NavBarSortModel model;
        list = new ArrayList();
        for (int i = 0; i < 50; i++) {
            model = new NavBarSortModel();
            model.setTitle("title is " + i);
            model.setIsSelect(false);
            list.add(model);
        }

        listView = (ListView) findViewById(R.id.listView);
        adapter = new MNavBarSortAdapter(this, list);
        listView.setAdapter(adapter);

    }
}
