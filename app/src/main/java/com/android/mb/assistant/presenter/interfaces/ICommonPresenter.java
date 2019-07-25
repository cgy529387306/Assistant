package com.android.mb.assistant.presenter.interfaces;

import java.util.Map;

/**
 * Created by cgy on 19/6/27.
 */

public interface ICommonPresenter {
    void requestData(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestCompetitive(String requestCode, Map<String,String> params, boolean isShowLoading);

    void requestGoods(String requestCode, Map<String,String> params, boolean isShowLoading);
}
