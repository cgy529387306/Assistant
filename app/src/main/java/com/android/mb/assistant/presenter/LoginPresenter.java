package com.android.mb.assistant.presenter;

import android.text.TextUtils;

import com.android.mb.assistant.api.ScheduleMethods;
import com.android.mb.assistant.base.BaseMvpPresenter;
import com.android.mb.assistant.presenter.interfaces.ILoginPresenter;
import com.android.mb.assistant.retrofit.http.exception.ApiException;
import com.android.mb.assistant.retrofit.http.exception.NoNetWorkException;
import com.android.mb.assistant.view.interfaces.ILoginView;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by cgy on 2018/2/11 0011.
 */

public class LoginPresenter extends BaseMvpPresenter<ILoginView> implements ILoginPresenter {


    @Override
    public void userLogin(Map<String,Object> requestMap) {
        mMvpView.showProgressDialog("登录中...");
        Observable observable = ScheduleMethods.getInstance().userLogin(requestMap);
        toSubscribe(observable,  new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(mMvpView!=null){
                    mMvpView.dismissProgressDialog();
                    if (e instanceof ApiException && !TextUtils.isEmpty(e.getMessage())){
                        mMvpView.showToastMessage(e.getMessage());
                    }else if (e instanceof NoNetWorkException && !TextUtils.isEmpty(e.getMessage())){
                        mMvpView.showToastMessage(e.getMessage());
                    }
                }
            }

            @Override
            public void onNext(Object result) {
                if (mMvpView!=null){
                    mMvpView.dismissProgressDialog();
                    mMvpView.loginSuccess(result);
                }
            }
        });
    }

}
