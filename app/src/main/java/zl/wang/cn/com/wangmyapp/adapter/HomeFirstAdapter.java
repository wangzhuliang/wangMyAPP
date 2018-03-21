package zl.wang.cn.com.wangmyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.model.Article;
import zl.wang.cn.com.wangmyapp.model.CMSBean;
import zl.wang.cn.com.wangmyapp.listener.OnItemClickListener;

/**
 * Created by 99142 on 2018/3/4.
 * homefirstadapter
 */

public class HomeFirstAdapter extends RecyclerView.Adapter<HomeFirstAdapter.VH>{

    private List<Article> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private Context context;
    private String image;
    private String text;
    private List<CMSBean.PostsBean> postsBeans;

    public HomeFirstAdapter(Context context, String image, String text, List<CMSBean.PostsBean> posts) {
        this.context = context;
        this.image = image;
        this.text = text;
        postsBeans = posts;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_first, parent, false);
        final VH holder = new VH(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder,"http://cms.youlin365.com"+postsBeans.get(position).getImage());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //Article item = mItems.get(position);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        ViewCompat.setTransitionName(holder.tvTitle, String.valueOf(position) + "_tv");


        //holder.img.setImageResource(item.getImgRes());
        //holder.tvTitle.setText(item.getTitle());

        Glide.with(context).load("http://cms.youlin365.com"+postsBeans.get(position).getImage()).into(holder.img);
        holder.tvTitle.setText(postsBeans.get(position).getTitle());
    }

    public void setDatas(List<Article> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return postsBeans.size();
    }

    public Article getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView img;

        public VH(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
