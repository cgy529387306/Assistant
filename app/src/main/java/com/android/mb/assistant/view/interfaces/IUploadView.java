package com.android.mb.assistant.view.interfaces;

import com.android.mb.assistant.base.BaseMvpView;

/**
 * Created by cgy on 19/6/27.
 */

public interface IUploadView extends BaseMvpView {
    void requestSuccess(String requestCode,String result);
    void uploadSuccess(String requestCode, String filePath,String result);
}
