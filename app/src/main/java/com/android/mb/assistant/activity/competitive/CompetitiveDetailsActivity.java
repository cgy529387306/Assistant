package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;

/**
 * 竞情详情
 */
public class CompetitiveDetailsActivity extends BaseActivity{

    @Override
    protected void loadIntent() {

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

    }

    @Override
    protected void setListener() {

    }

    private void initView() {
    }

}
