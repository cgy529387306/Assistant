package com.android.mb.assistant.presenter;

import com.android.mb.assistant.api.ScheduleMethods;
import com.android.mb.assistant.base.BaseMvpPresenter;
import com.android.mb.assistant.presenter.interfaces.ICommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.view.interfaces.ICommonView;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by cgy on 2018/2/11 0011.
 */

public class CommonPresenter extends BaseMvpPresenter<ICommonView> implements ICommonPresenter {


    @Override
    public void requestData(String requestCode, List<String> params, boolean isShowLoading) {
        if (isShowLoading){
            mMvpView.showProgressDialog("请稍后...");
        }
        Observable observable = ScheduleMethods.getInstance().baseRequest(requestCode,params);
        toSubscribe(observable,  new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(mMvpView!=null){
                    mMvpView.dismissProgressDialog();
                    mMvpView.showToastMessage(Helper.isNotEmpty(e.getMessage())?e.getMessage():"请求失败");
                }
            }

            @Override
            public void onNext(String result) {
                if (mMvpView!=null){
                    mMvpView.dismissProgressDialog();
                    if (Helper.isNotEmpty(result)){
                        mMvpView.requestSuccess(result);
                    }else{
                        mMvpView.showToastMessage("服务端数据异常");
                    }
                }
            }
        });
    }

}
