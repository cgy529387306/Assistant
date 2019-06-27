package com.android.mb.assistant.presenter.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by cgy on 19/6/27.
 */

public interface ICommonPresenter {
    void requestData(String requestCode, List<String> params, boolean isShowLoading);
}
