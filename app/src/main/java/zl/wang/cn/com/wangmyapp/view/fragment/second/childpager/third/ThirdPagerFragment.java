package zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.third;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.Arrays;
import java.util.Collection;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.BaseRecyclerAdapter;
import zl.wang.cn.com.wangmyapp.adapter.SmartViewHolder;
import zl.wang.cn.com.wangmyapp.utils.StatusBarUtil;

/**
 * Created by hahaha on 2018/3/7.
 * 餐饮美食
 */

public class ThirdPagerFragment extends SupportFragment {

    private static final String ARG_TYPE = "arg_type";

    public static ThirdPagerFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE, title);
        ThirdPagerFragment fragment = new ThirdPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class Model {
        int imageId;
        int avatarId;
        String name;
        String nickname;
    }

    private static boolean isFirstEnter = true;
    private BaseRecyclerAdapter<Model> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTitle = getArguments().getString(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_pager_third, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        final RelativeLayout rl_toobar = view.findViewById(R.id.rl_toobar);
        final RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);

        //第一次进入演示刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            refreshLayout.autoRefresh();
        }

        //初始化列表和监听
        View v = view.findViewById(R.id.recyclerView);
        if (v instanceof RecyclerView) {
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            RecyclerView recyclerView = (RecyclerView) v;
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<Model>(loadModels(), R.layout.listitem_practive_repast) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Model model, int position) {
                    holder.text(R.id.name, model.name);
                    holder.text(R.id.nickname, model.nickname);
                    holder.image(R.id.image, model.imageId);
                    holder.image(R.id.avatar, model.avatarId);
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
                            if (mAdapter.getCount() > 12) {
                                Toast.makeText(getActivity().getBaseContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                                refreshLayout.finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
                            } else {
                                mAdapter.loadMore(loadModels());
                                refreshLayout.finishLoadMore();
                            }
                        }
                    }, 1000);
                }
            });

            refreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setHeaderInsetStart(0);
                }
            }, 500);
        }

        //状态栏透明和间距处理
        //StatusBarUtil.darkMode(getActivity());
        //StatusBarUtil.setPaddingSmart(getActivity(), view);
        //StatusBarUtil.setPaddingSmart(getActivity(), rl_toobar);
        //StatusBarUtil.setPaddingSmart(getActivity(), findViewById(R.id.blurview));
    }

    /**
     * 模拟数据
     */
    private Collection<Model> loadModels() {
        return Arrays.asList(
                new Model() {{
                    this.name = "但家香酥鸭";
                    this.nickname = "爱过那张脸";
                    this.imageId = R.mipmap.image_practice_repast_1;
                    this.avatarId = R.mipmap.image_avatar_1;
                }}, new Model() {{
                    this.name = "香菇蒸鸟蛋";
                    this.nickname = "淑女算个鸟";
                    this.imageId = R.mipmap.image_practice_repast_2;
                    this.avatarId = R.mipmap.image_avatar_2;
                }}, new Model() {{
                    this.name = "花溪牛肉粉";
                    this.nickname = "性感妩媚";
                    this.imageId = R.mipmap.image_practice_repast_3;
                    this.avatarId = R.mipmap.image_avatar_3;
                }}, new Model() {{
                    this.name = "破酥包";
                    this.nickname = "一丝丝纯真";
                    this.imageId = R.mipmap.image_practice_repast_4;
                    this.avatarId = R.mipmap.image_avatar_4;
                }}, new Model() {{
                    this.name = "盐菜饭";
                    this.nickname = "等着你回来";
                    this.imageId = R.mipmap.image_practice_repast_5;
                    this.avatarId = R.mipmap.image_avatar_5;
                }}, new Model() {{
                    this.name = "米豆腐";
                    this.nickname = "宝宝树人";
                    this.imageId = R.mipmap.image_practice_repast_6;
                    this.avatarId = R.mipmap.image_avatar_6;
                }});
    }
}
