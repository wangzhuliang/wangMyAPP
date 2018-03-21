package zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.second;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.OnTwoLevelListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.io.IOException;

import me.panpf.sketch.SketchImageView;
import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.utils.StatusBarUtil;

/**
 * Created by hahaha on 2018/3/7.
 * 二楼有惊喜
 */

public class SecondPagerFragment extends SupportFragment {

    private static final String ARG_TYPE = "arg_type";

    public static SecondPagerFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE, title);
        SecondPagerFragment fragment = new SecondPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTitle = getArguments().getString(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_pager_second, container, false);
        initView(view);
        return view;
    }

    private void initView(final View view) {

        final View floor = view.findViewById(R.id.secondfloor);
        final TwoLevelHeader header = (TwoLevelHeader)view.findViewById(R.id.header);
        final RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        SketchImageView sketchImageView = view.findViewById(R.id.sketchImageView);
        sketchImageView.displayResourceImage(R.mipmap.iv_poster);

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int extendHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, extendHeight);
                //toolbar.setAlpha(1 - Math.min(percent, 1));
                /*floor.setTranslationY(Math.min(offset - floor.getHeight() + toolbar.getHeight(),
                        refreshLayout.getLayout().getHeight() - floor.getHeight()));*/
                floor.setTranslationY(Math.min(offset - floor.getHeight(),
                        refreshLayout.getLayout().getHeight() - floor.getHeight()));
            }
        });

        header.setOnTwoLevelListener(new OnTwoLevelListener() {
            @Override
            public boolean onTwoLevel(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getContext(),"触发二楼事件",Toast.LENGTH_SHORT).show();
                view.findViewById(R.id.secondfloor_content).animate().alpha(1).setDuration(2000);
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        header.finishTwoLevel();
                        view.findViewById(R.id.secondfloor_content).animate().alpha(0).setDuration(1000);
                    }
                },5000);
                return true;//true 将会展开二楼状态 false 关闭刷新
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getContext(),"触发刷新事件",Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh(2000);
            }
        });

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        refreshLayout.setNoMoreData(false);//恢复上拉状态
                    }
                }, 2000);
            }
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*if (mAdapter.getCount() > 12) {
                            Toast.makeText(getActivity().getBaseContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshLayout.finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
                        } else {
                            mAdapter.loadMore(loadModels());
                            refreshLayout.finishLoadMore();
                        }*/
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }
        });

        //状态栏透明和间距处理
        //StatusBarUtil.immersive(getActivity());
       // StatusBarUtil.setMargin(getActivity(),  view.findViewById(R.id.classics));
        //StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.rl_toobar));
        //StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.contentPanel));
    }
}
