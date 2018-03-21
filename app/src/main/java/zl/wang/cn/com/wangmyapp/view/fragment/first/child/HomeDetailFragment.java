package zl.wang.cn.com.wangmyapp.view.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.base.BaseBackFragment;
import zl.wang.cn.com.wangmyapp.model.Article;
import zl.wang.cn.com.wangmyapp.view.fragment.CycleFragment;

/**
 * Created by 99142 on 2018/3/4.
 * HomeDetailFragment
 */

public class HomeDetailFragment extends BaseBackFragment {

    private static final String ARG_ITEM = "arg_item";

    private Article mArticle;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private FloatingActionButton mFab;

    public static HomeDetailFragment newInstance(Article article) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, article);
        HomeDetailFragment fragment = new HomeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticle = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mImgDetail = (ImageView) view.findViewById(R.id.img_detail);
        mTvTitle = (TextView) view.findViewById(R.id.tv_content);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mToolbar.setTitle("");
        initToolbarNav(mToolbar);
        Glide.with(getActivity()).load(mArticle.getContent()).into(mImgDetail);
        //mImgDetail.setImageResource(mArticle.getImgRes());
        mTvTitle.setText(mArticle.getTitle());

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }
}
