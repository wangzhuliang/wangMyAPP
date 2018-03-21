package zl.wang.cn.com.wangmyapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.utils.ClickableMovementMethod;
import zl.wang.cn.com.wangmyapp.utils.SpannableStringUtils;
import zl.wang.cn.com.wangmyapp.utils.ToastUtils;
import zl.wang.cn.com.wangmyapp.utils.Utils;
import zl.wang.cn.com.wangmyapp.view.fragment.third.entity.Status;
import zl.wang.cn.com.wangmyapp.view.fragment.third.data.DataServer;

/**
 * 文 件 名: AnimationAdapter
 * 创 建 人: 动画刷新头尾加载
 * 创建日期: 16/12/24 15:33
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class AnimationAdapter extends BaseQuickAdapter<Status, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {


    public AnimationAdapter() {
        super(R.layout.layout_animation, DataServer.getSampleData(1));
        //addItemType(ClickEntity.CLICK_ITEM_VIEW, R.layout.item_click_view);

    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.img).addOnClickListener(R.id.tweetName);
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.img, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.mipmap.animation_img3);
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ((TextView) helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ((TextView) helper.getView(R.id.tweetText)).setMovementMethod(ClickableMovementMethod.getInstance());
        ((TextView) helper.getView(R.id.tweetText)).setFocusable(false);
        ((TextView) helper.getView(R.id.tweetText)).setClickable(false);
        ((TextView) helper.getView(R.id.tweetText)).setLongClickable(false);


        switch (helper.getItemViewType()) {
            /*helper.addOnClickListener(R.id.btn);
            helper.addOnLongClickListener(R.id.btn);
            helper.addOnClickListener(R.id.iv_num_reduce).addOnClickListener(R.id.iv_num_add)*/

            /*在内部再添加一个recyclerview，暂时不多做介绍
            helper.setNestView(R.id.item_click);
            NestAdapter nestAdapter;
            final RecyclerView recyclerView = helper.getView(R.id.nest_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setHasFixedSize(true);
            nestAdapter = new NestAdapter();
            nestAdapter.setOnItemClickListener(this);
            nestAdapter.setOnItemChildClickListener(this);
            recyclerView.setAdapter(nestAdapter);*/
        }

    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Utils.getContext().getResources().getColor(R.color.orange));
            ds.setUnderlineText(true);
        }
    };

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(Utils.getContext(), "childView click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //Logger.d("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
        Toast.makeText(Utils.getContext(), "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_SHORT).show();
    }
}
