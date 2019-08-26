package com.android.mb.assistant.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.entitys.NoticeBean;

public class MsgDetailActivity extends BaseActivity {

    private TextView mTvTitle;
    private TextView mTvTime;
    private TextView mTvContent;
    private NoticeBean mNoticeBean;

    @Override
    protected void loadIntent() {
        mNoticeBean = (NoticeBean) getIntent().getSerializableExtra("mNoticeBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_msg_detail;
    }

    @Override
    protected void initTitle() {
        setTitleText("公告详情");
    }

    @Override
    protected void bindViews() {
        mTvTitle = findViewById(R.id.tv_title1);
        mTvTime = findViewById(R.id.tv_time);
        mTvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        if (mNoticeBean!=null){
            mTvTitle.setText(mNoticeBean.getMtitle());
            mTvTime.setText(mNoticeBean.getMcratetime());
            mTvContent.setText(mNoticeBean.getMcontent());
        }
    }

    @Override
    protected void setListener() {

    }
}
