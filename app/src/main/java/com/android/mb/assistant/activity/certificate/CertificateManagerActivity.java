package com.android.mb.assistant.activity.certificate;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.goods.ApplyListActivity;
import com.android.mb.assistant.activity.goods.GoodsInputActivity;
import com.android.mb.assistant.activity.goods.GoodsManagerActivity;
import com.android.mb.assistant.adapter.GoodsManageAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.widget.NestedGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 *
 * 电子凭证管理
 */
public class CertificateManagerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void loadIntent() {
        
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certificate_manage;
    }

    @Override
    protected void initTitle() {
        setTitleText("电子凭证");
    }

    @Override
    protected void bindViews() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


    @Override
    protected void setListener() {
        findViewById(R.id.ll_certificate_input).setOnClickListener(this);
        findViewById(R.id.ll_certificate_mine).setOnClickListener(this);
        findViewById(R.id.ll_certificate_audit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_certificate_input){
            NavigationHelper.startActivity(CertificateManagerActivity.this, CertificateInputActivity.class,null,false);
        }else if (id == R.id.ll_certificate_mine){
            Bundle bundle = new Bundle();
            bundle.putInt("type",1);
            NavigationHelper.startActivity(CertificateManagerActivity.this, CertificateListActivity.class,bundle,false);
        }else if (id == R.id.ll_certificate_audit){
            Bundle bundle = new Bundle();
            bundle.putInt("type",0);
            NavigationHelper.startActivity(CertificateManagerActivity.this, CertificateListActivity.class,bundle,false);
        }
    }
}
