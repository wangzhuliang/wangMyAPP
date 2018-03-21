package zl.wang.cn.com.wangmyapp.presenter;

import retrofit2.Response;
import zl.wang.cn.com.wangmyapp.listener.LoadTasksCallBack;
import zl.wang.cn.com.wangmyapp.model.CMSBean;
import zl.wang.cn.com.wangmyapp.net.WangTask;
import zl.wang.cn.com.wangmyapp.contract.WangContract;

/**
 * Created by 99142 on 2018/2/28.
 * 实现Presenter接口
 */

public class WangPresenter implements WangContract.Presenter,LoadTasksCallBack<Response<CMSBean>> {


    private WangTask wangTask;
    private WangContract.View addTaskView;

    public WangPresenter(WangContract.View addTaskView, WangTask wangTask) {
        this.wangTask = wangTask;
        this.addTaskView = addTaskView;
    }

    @Override
    public void onSuccess(Response<CMSBean> cmsBeanResponse) {
        //if (addTaskView.isActive()){
            addTaskView.getWang(cmsBeanResponse);
        //}
    }

    @Override
    public void onStart() {
        //if (addTaskView.isActive()){
            addTaskView.showLoading();
        //}
    }

    @Override
    public void onFailed() {
        //if (addTaskView.isActive()){
            addTaskView.showError();
            addTaskView.hideLoading();
        //}
    }

    @Override
    public void onFinish() {
        //if (addTaskView.isActive()){
            addTaskView.hideLoading();
        //}
    }

    @Override
    public void setWang(String code) {
        wangTask.execute(code,this);
    }
}
