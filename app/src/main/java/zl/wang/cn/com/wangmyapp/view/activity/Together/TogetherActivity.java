package zl.wang.cn.com.wangmyapp.view.activity.Together;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.gs.buluo.common.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;

public class TogetherActivity extends AppCompatActivity implements ScrollViewListener {

    WalletHeadRecyclerView rvHead;
    WalletFootRecyclerView rvFoot;
    private WalletHeaderAdapter adapter;
    private WalletFootAdapter adapterFoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_together);

        initUI();
    }

    private void initUI() {
        rvHead = findViewById(R.id.rv_head);
        rvFoot = findViewById(R.id.rv_foot);
        rvFoot.setOnScrollViewListener(this);
        rvHead.setOnScrollViewListener(this);
        initRvHead();
        initRvFoot();
    }

    private void initRvHead() {
        List list = new ArrayList();
        list.add("2");
        list.add("3");
        list.add("4");

        adapter = new WalletHeaderAdapter(R.layout.item_wallet_header, list);
        rvHead.setAdapter(adapter);
        initHeadFoot();
    }

    private void initRvFoot() {
        List list = new ArrayList<>();
        list.add("2");
        list.add("3");
        list.add("4");
        adapterFoot = new WalletFootAdapter(rvFoot);
        adapterFoot.setData(list);
        rvFoot.setAdapter(adapterFoot);
    }

    /**
     * 为上面的recyclerView添加头布局和尾布局
     */
    private void initHeadFoot() {
        View head = LayoutInflater.from(this).inflate(R.layout.wallet_header_foot, rvHead, false);
        View foot = LayoutInflater.from(this).inflate(R.layout.wallet_header_foot, rvHead, false);
        int width = DensityUtils.dip2px(this, 20);
        int width1 = DensityUtils.dip2px(this, 40);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) head.getLayoutParams();
        params.width = width;
        head.setLayoutParams(params);
        adapter.addHeaderView(head, 0, 0);
        RecyclerView.LayoutParams params1 = (RecyclerView.LayoutParams) foot.getLayoutParams();
        params1.width = width1;
        foot.setLayoutParams(params1);
        adapter.addFooterView(foot, 0, 0);
    }


    @Override
    public void onScrollChanged(Object scrollView, int x, int y) {

        int width1 = CommonUtil.getScreenWidth(this) - DensityUtils.dip2px(this, 60);
        int width2 = CommonUtil.getScreenWidth(this);
        if (scrollView == rvHead) {
            rvFoot.setmark(false);
            rvFoot.scrollTo(x * width2 / width1, y);
        } else if (scrollView == rvFoot) {
            rvHead.setmark(false);

            rvHead.scrollTo(x * width1 / width2, y);
        }
        rvHead.setmark(true);
        rvFoot.setmark(true);
    }
}
