package com.android.mb.assistant.presenter;

import com.android.mb.assistant.api.ScheduleMethods;
import com.android.mb.assistant.base.BaseMvpPresenter;
import com.android.mb.assistant.presenter.interfaces.IUploadPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.view.interfaces.IUploadView;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by cgy on 2018/2/11 0011.
 */

public class UploadPresenter extends BaseMvpPresenter<IUploadView> implements IUploadPresenter {


    @Override
    public void requestData(String requestCode, Map<String,String> params, boolean isShowLoading) {
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
                        Logger.json(result);
                        mMvpView.requestSuccess(requestCode,result);
                    }else{
                        mMvpView.showToastMessage("服务端数据异常");
                    }
                }
            }
        });
    }

    @Override
    public void requestCompetitive(String requestCode, Map<String, String> params, boolean isShowLoading) {
        if (isShowLoading){
            mMvpView.showProgressDialog("请稍后...");
        }
        Observable observable = ScheduleMethods.getInstance().competitiveRequest(requestCode,params);
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
                        Logger.json(result);
                        mMvpView.requestSuccess(requestCode,result);
                    }else{
                        mMvpView.showToastMessage("服务端数据异常");
                    }
                }
            }
        });
    }

    @Override
    public void requestGoods(String requestCode, Map<String, String> params, boolean isShowLoading) {
        if (isShowLoading){
            mMvpView.showProgressDialog("请稍后...");
        }
        Observable observable = ScheduleMethods.getInstance().goodsRequest(requestCode,params);
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
                        Logger.json(result);
                        mMvpView.requestSuccess(requestCode,result);
                    }else{
                        mMvpView.showToastMessage("服务端数据异常");
                    }
                }
            }
        });
    }

    @Override
    public void uploadImg(String requestCode, String filePath, boolean isShowLoading) {
        if (isShowLoading){
            mMvpView.showProgressDialog("请稍后...");
        }
        Observable observable = ScheduleMethods.getInstance().uploadImg(requestCode,new HashMap<>(),new File(filePath));
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
                        Logger.json(result);
                        mMvpView.uploadSuccess(requestCode,filePath,result);
                    }else{
                        mMvpView.showToastMessage("服务端数据异常");
                    }
                }
            }
        });
    }


}
