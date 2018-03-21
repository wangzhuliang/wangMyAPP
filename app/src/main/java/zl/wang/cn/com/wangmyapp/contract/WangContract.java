package zl.wang.cn.com.wangmyapp.contract;

import retrofit2.Response;
import zl.wang.cn.com.wangmyapp.base.BaseView;
import zl.wang.cn.com.wangmyapp.model.CMSBean;

/**
 * Created by 99142 on 2018/2/28.
 * 契约接口存放相同业务的Presenter和View
 */

public interface WangContract {

    interface Presenter {
        void setWang(String code);
    }

    interface View extends BaseView<Presenter> {

        void getWang(Response<CMSBean> cmsBeanResponse);
        void showLoading();
        void hideLoading();
        void showError();
        //判断Fragment是否添加到了Activity
        boolean isActive();
    }

}
