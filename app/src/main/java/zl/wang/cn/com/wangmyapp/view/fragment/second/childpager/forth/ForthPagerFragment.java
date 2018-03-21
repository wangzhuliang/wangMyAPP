package zl.wang.cn.com.wangmyapp.view.fragment.second.childpager.forth;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.BaseRecyclerAdapter;
import zl.wang.cn.com.wangmyapp.adapter.SmartViewHolder;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by hahaha on 2018/3/7.
 * 整体嵌套
 */

public class ForthPagerFragment extends SupportFragment implements AdapterView.OnItemClickListener{

    private static final String ARG_TYPE = "arg_type";

    public static ForthPagerFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE, title);
        ForthPagerFragment fragment = new ForthPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BaseRecyclerAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTitle = getArguments().getString(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_pager_forth, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        final List<String> listImages = new ArrayList<>();
        listImages.add("R.drawable.image_secondfloor");
        listImages.add("R.drawable.image_secondfloor_content");
        listImages.add("R.drawable.image_secondfloor");
        listImages.add("R.drawable.image_secondfloor_content");
        listImages.add("R.drawable.image_secondfloor");
        listImages.add("R.drawable.image_secondfloor_content");

        Banner banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        banner.setImages(listImages);
        banner.start();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter(listImages,simple_list_item_2,ForthPagerFragment.this) {

            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Object model, int position) {
                holder.text(android.R.id.text1,"一杯敬自由");
                holder.text(android.R.id.text2, "一杯敬死亡");
                holder.textColorId(android.R.id.text2, R.color.pink);
                holder.textColorId(android.R.id.text2, R.color.blue);
            }
        });

        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMore(listImages);
                        refreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private class BannerImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.mipmap.image_secondfloor_content);
            //Glide.with(context).load(path).into(imageView);
        }
    }
}
