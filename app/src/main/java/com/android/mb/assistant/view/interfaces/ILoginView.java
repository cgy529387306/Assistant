package com.android.mb.assistant.view.interfaces;

import com.android.mb.assistant.base.BaseMvpView;
import com.android.mb.assistant.entitys.UserBean;

/**
 * Created by cgy on 2018/2/11 0011.
 */
public interface ILoginView extends BaseMvpView {
    void loginSuccess(UserBean result);
}
