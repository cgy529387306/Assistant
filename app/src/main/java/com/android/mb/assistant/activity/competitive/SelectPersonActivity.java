package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.view.interfaces.ICommonView;

import java.util.HashMap;
import java.util.Map;

/**
 * 选择人员
 */
public class SelectPersonActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView{



    @Override
    protected void loadIntent() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competitive_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("选择人员");
    }

    @Override
    protected void bindViews() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mCurrentPage = 1;
        getListFormServer();
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void requestSuccess(String result) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("uName",CurrentUser.getInstance().getUname());
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DISPATCHERS,requestParams,false);
    }
}
