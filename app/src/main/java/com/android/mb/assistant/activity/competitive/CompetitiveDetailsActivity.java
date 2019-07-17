package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.view.interfaces.ICommonView;

import java.util.HashMap;
import java.util.Map;

/**
 * 竞情详情
 */
public class CompetitiveDetailsActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView{

    private CompetitiveBean mCompetitiveBean;

    @Override
    protected void loadIntent() {
        mCompetitiveBean = (CompetitiveBean) getIntent().getSerializableExtra("cometitive");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competitive_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("竞情详情");
    }

    @Override
    protected void bindViews() {
        initView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        if (mCompetitiveBean!=null){
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("cId",mCompetitiveBean.getCId());
            mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DETAIL,requestParams,false);
        }
    }

    @Override
    protected void setListener() {

    }

    private void initView() {
    }

    @Override
    public void requestSuccess(String result) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }
}
