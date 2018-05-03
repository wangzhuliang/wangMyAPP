package zl.wang.cn.com.wangmyapp.view.fragment.four.child;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.fragment.four.entity.Datum;

/**
 * Created by hahaha on 2018/3/13.
 * 仿最美有物
 */
public class TvFirstFragment extends SupportFragment {

    protected DeckView<Datum> mDeckView;
    private ArrayList<Datum> mList;
    Drawable mDefaultHeaderIcon;
    Bitmap mdefaultThumnail;//占位符当正在下载图片
    Bitmap mDefaultTarget_image;
    Bitmap mDefaultTargetIcon;
    private int scrollToChildIndex = -1;

    public static TvFirstFragment newInstance() {

        Bundle args = new Bundle();

        TvFirstFragment fragment = new TvFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mDeckView = view.findViewById(R.id.deckview);

        mdefaultThumnail = BitmapFactory.decodeResource(getResources(), R.mipmap.default_thumbnail);
        //左上角图片
        mDefaultHeaderIcon = getResources().getDrawable(R.mipmap.default_header_icon);
        mList = new ArrayList<>();

        DeckView.Callback<Datum> deckViewCallBack = new DeckView.Callback<Datum>() {
            @Override
            public ArrayList<Datum> getData() {
                return mList;
            }

            @Override
            public void loadViewData(WeakReference<DeckChildView<Datum>> dcv, Datum item) {
                loadViewDataInternal(item, dcv);
            }

            @Override
            public void unloadViewData(Datum item) {

                Picasso.with(getContext()).cancelRequest(item.target_image);
                Picasso.with(getContext()).cancelRequest(item.target_avatar_icon);
            }

            @Override
            public void onViewDismissed(Datum item) {
                //L("onViewDismissed--> ");
                mList.remove(item);
                mDeckView.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(Datum item) {
                Toast.makeText(getActivity(),"你点击了",Toast.LENGTH_SHORT).show();
                //L("onItemClick--> " + item.id);
            }

            @Override
            public void onNoViewsToDeck() {
                //L("onNoViewsToDeck--> ");
            }
        };
        //添加上面的接口对象
        mDeckView.initialize(deckViewCallBack);


        if (scrollToChildIndex != -1) {
            mDeckView.post(new Runnable() {
                @Override
                public void run() {
                    //恢复到滚动位置
                    mDeckView.scrollToChild(scrollToChildIndex);
                }
            });
        }

        for (int i = 0; i < 12; i++) {
            Datum datum = new Datum();
            datum.id = i+1;

            datum.iconTitle = "但双方都不v";
            datum.subTitle = "小标题小标题小标题小标题小标题";
            datum.title = "每个心中都有一个填补空白的恶童" + i;
            datum.avatar_url = "http://img1.ph.126.net/z-JOfX9n2dgJeayVjX8OwA==/6608880720562116694.jpg";
            datum.image_url = "http://img1.ph.126.net/z-JOfX9n2dgJeayVjX8OwA==/6608880720562116694.jpg";

            mList.add(0, datum);
        }
        mDeckView.notifyDataSetChanged();

    }

    void loadViewDataInternal(final Datum datum, final WeakReference<DeckChildView<Datum>> weakView) {
        // datum.target can be null
        Picasso.with(getContext()).cancelRequest(datum.target_image);
        Picasso.with(getContext()).cancelRequest(datum.target_avatar_icon);
        /**
         * 这里设置大图;
         * */
        datum.target_image = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Pass loaded Bitmap to view
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, bitmap,
                            datum.iconTitle, datum.title, datum.subTitle);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Loading failed. Pass default thumbnail instead
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultTarget_image,
                            "Failed", "Failed", "Failed");
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Pass the default thumbnail for now. It will
                // be replaced once the target Bitmap has been loaded
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultTarget_image,
                            "", "", "");
                }
                //by kesp,不显示好看一点;
            }
        };
        /**
         * 这里设置小头像,icon_image.
         * */
        datum.target_avatar_icon = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum, bitmap);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum, mDefaultTargetIcon);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum, mDefaultTargetIcon);
                }
            }
        };

        // Begin loading
        Picasso.with(getContext()).load(datum.image_url).into(datum.target_image);
        Picasso.with(getContext()).load(datum.avatar_url).into(datum.target_avatar_icon);

    }




}
