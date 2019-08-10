package com.android.mb.assistant.presenter.interfaces;

import java.io.File;
import java.util.Map;

/**
 * Created by cgy on 19/6/27.
 */

public interface ICommonPresenter {
    void requestData(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestCompetitive(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestGoods(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestWork(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestCart(String requestCode, Map<String,String> params, boolean isShowLoading);

    void uploadImg(String requestCode, Map<String,String> params, File file, boolean isShowLoading);
}
