package zl.wang.cn.com.wangmyapp.custom_view;

import android.view.View;

/**
 * Created by cpf on 2018/1/16.
 */

public class ExampleStickyView implements StickyView {

    @Override
    public boolean isStickyView(View view) {
        return (Boolean) view.getTag();
    }

    @Override
    public int getStickViewType() {
        return 11;
    }
}
