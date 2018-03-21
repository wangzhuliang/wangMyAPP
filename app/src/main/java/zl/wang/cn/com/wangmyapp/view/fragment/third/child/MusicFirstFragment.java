package zl.wang.cn.com.wangmyapp.view.fragment.third.child;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.AnimationAdapter;
import zl.wang.cn.com.wangmyapp.view.activity.VideoActivity;
import zl.wang.cn.com.wangmyapp.view.fragment.third.animation.CustomAnimation;
import zl.wang.cn.com.wangmyapp.view.fragment.third.entity.Status;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by hahaha on 2018/3/9.
 * recycleview总结
 */

public class MusicFirstFragment extends SupportFragment{

    private RecyclerView mRecyclerView;
    private AnimationAdapter mAnimationAdapter;
    private ImageView mImgBtn;
    private int mFirstPageItemCount = 3;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static MusicFirstFragment newInstance() {

        Bundle args = new Bundle();
        MusicFirstFragment fragment = new MusicFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_first, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initAdapter();
        initMenu(view);
        initRefreshLayout();
    }

    private void initAdapter() {

        mAnimationAdapter = new AnimationAdapter();
        mAnimationAdapter.openLoadAnimation();
        mAnimationAdapter.setNotDoAnimationCount(mFirstPageItemCount);
        mAnimationAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        },mRecyclerView);

        //可以adapter替代
        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String content = null;
                Status status = (Status) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.img:
                        content = "img:" + status.getUserAvatar();
                        //Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
                    case R.id.tweetName:
                        content = "name:" + status.getUserName();
                        //Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
                    case R.id.tweetText:
                        content = "tweetText:" + status.getUserName();
                        //Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
                        // you have set clickspan .so there should not solve any click event ,just empty
                        Intent intent = new Intent(getActivity(),VideoActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });

        mAnimationAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //Log.d(TAG, "onItemLongClick: ");
                //Toast.makeText(ItemClickActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        View headerView = getHeaderView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
            }
        });
        mAnimationAdapter.addHeaderView(headerView);


        View footerView = getFooterView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
            }
        });
        mAnimationAdapter.addFooterView(footerView, 0);

        mRecyclerView.setAdapter(mAnimationAdapter);
    }

    private void initMenu(View view) {

        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.spinner);
        spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 1:
                        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        break;
                    case 2:
                        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 3:
                        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                    case 5:
                        mAnimationAdapter.openLoadAnimation(new CustomAnimation());
                        break;
                    default:
                        break;
                }
                mRecyclerView.setAdapter(mAnimationAdapter);
            }
        });
        mAnimationAdapter.isFirstOnly(false);//init firstOnly state
        SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    mAnimationAdapter.isFirstOnly(true);
                } else {
                    mAnimationAdapter.isFirstOnly(false);
                }
                mAnimationAdapter.notifyDataSetChanged();
            }
        });

        mImgBtn = (ImageView) view.findViewById(R.id.img_back);
        mImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }


    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationAdapter.removeHeaderView(v);
            }
        };
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationAdapter.removeFooterView(v);
            }
        };
    }

    private void loadMore() {
        /*网络请求
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {

            }
            @Override
            public void fail(Exception e) {

                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
            }
        }).start();*/
        List<Status> data = new ArrayList<Status>();
        for (int i = 0; i < 5; i++) {
            Status status = new Status();
            status.setText("aaa"+i);
            data.add(status);
        }

        //加载成功
        setData(false, data);
        //加载失败
        //mAnimationAdapter.loadMoreFail();
    }

    private void setData(boolean isRefresh, List<Status> data) {
        if (isRefresh) {
            mAnimationAdapter.setNewData(data);
        } else {
            if (data.size() > 0) {
                mAnimationAdapter.addData(data);
            }
        }
        //第一页如果不够一页就不显示没有更多数据布局
        if (data.size() < 3) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAnimationAdapter.loadMoreEnd(isRefresh);
            //Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            //模拟加载
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    mAnimationAdapter.loadMoreComplete();
                }
            }, 3000);   //3秒

        }
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                refresh();
            }
        });
    }

    private void refresh() {
        mAnimationAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        List<Status> data = new ArrayList<Status>();
        for (int i = 0; i < 5; i++) {
            Status status = new Status();
            status.setText("aaa"+i);
            data.add(status);
        }
        setData(true, data);
        mAnimationAdapter.setEnableLoadMore(true);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
