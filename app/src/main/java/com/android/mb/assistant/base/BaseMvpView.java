package com.android.mb.assistant.base;

/**
 * @Description
 * @Created by cgy on 2017/7/19
 * @Version v1.0
 */

public interface BaseMvpView {

    /**
     * 回退.
     */
    void back();

    /**
     * 提示Toast
     * @param message
     */
    void showToastMessage(String message);

    /**
     * 显示进度加载框
     */
    void showProgressDialog(String message);

    /**
     * 隐藏进度加载框.
     */
    void dismissProgressDialog();
}
