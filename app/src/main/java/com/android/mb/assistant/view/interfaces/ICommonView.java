package com.android.mb.assistant.view.interfaces;

import com.android.mb.assistant.base.BaseMvpView;
import com.android.mb.assistant.entitys.UserBean;

/**
 * Created by cgy on 19/6/27.
 */

public interface ICommonView extends BaseMvpView {
    void requestSuccess(String requestCode,String result);
}
