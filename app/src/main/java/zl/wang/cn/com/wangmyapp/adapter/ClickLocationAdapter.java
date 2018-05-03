package zl.wang.cn.com.wangmyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zl.wang.cn.com.wangmyapp.R;

public class ClickLocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> beans;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private int height = 0;
    private ClickLocationAdapterListener clickLocationAdapterListener;

    public ClickLocationAdapter(List<String> beans, Context context) {
        this.beans = beans;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        clickLocationAdapterListener = (ClickLocationAdapterListener) context;
    }

    enum TYPE {
        TOP,
        NORMAL,
        BOTTOM
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE.NORMAL.ordinal()){
            return new NormalViewHolder(mLayoutInflater.inflate(R.layout.adapter_normal, parent, false));
        }else if (viewType == TYPE.TOP.ordinal()){
            return new TopViewHolder(mLayoutInflater.inflate(R.layout.adapter_top, parent, false));
        }else {
            View view = mLayoutInflater.inflate(R.layout.adapter_bottom, parent, false);
            view.getLayoutParams().height = height;
            return new BottomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).tv_title.setText(beans.get(position));
            ((NormalViewHolder) holder).tv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickLocationAdapterListener.showInputComment(v,position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (beans.get(position).equals("")) {
            return TYPE.TOP.ordinal();
        }else if (beans.get(position).equals("a")){
            return TYPE.BOTTOM.ordinal();
        }else {
            return TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_comment;
        NormalViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_comment= view.findViewById(R.id.tv_comment);
        }
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {

        TopViewHolder(View view) {
            super(view);

        }
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {

        BottomViewHolder(View view) {
            super(view);

        }
    }

    public interface ClickLocationAdapterListener {
        void showInputComment(View view, int i);
    }
}
