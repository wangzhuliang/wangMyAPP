package zl.wang.cn.com.wangmyapp.view.activity.tagview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * author: shell
 * date 2016/12/20 下午3:43
 **/
public class TagTextView extends TextView implements ITagView {

    private DIRECTION mDirection;

    public TagTextView(Context context) {
        this(context, null);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTextColor(Color.WHITE);
        setTextSize(13);
        setShadowLayer(7, 0, 0, Color.BLACK);
        setPadding(DipConvertUtils.dip2px(getContext(), 12), DipConvertUtils.dip2px(getContext(), 4)
                , DipConvertUtils.dip2px(getContext(), 12), DipConvertUtils.dip2px(getContext(), 4));
    }

    @Override
    public void setDirection(DIRECTION direction) {
        mDirection = direction;
    }

    @Override
    public DIRECTION getDirection() {
        return mDirection;
    }
}
