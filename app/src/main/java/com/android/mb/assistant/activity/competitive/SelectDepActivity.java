package com.android.mb.assistant.activity.competitive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.CityAdapter;
import com.android.mb.assistant.adapter.DepAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CityBean;
import com.android.mb.assistant.entitys.CityListResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 选择部门
 */
public class SelectDepActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, BaseQuickAdapter.OnItemClickListener, OnRefreshListener, OnLoadMoreListener {

    private DepAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    @Override
    protected void loadIntent() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_recycleview;
    }

    @Override
    protected void initTitle() {
        setTitleText("选择部门");
    }

    @Override
    protected void bindViews() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(true);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(1),getResources().getColor(R.color.list_divider)));
        mAdapter = new DepAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mCurrentPage = 1;
        getListFormServer();
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_COMMON_CITY,requestParams,false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CityBean cityBean = mAdapter.getData().get(position);
        Intent intent = new Intent();
        intent.putExtra("city",cityBean);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage++;
        getListFormServer();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        getListFormServer();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        CityListResp listResp = JsonHelper.fromJson(result, CityListResp.class);
        if (listResp!=null){
            if (listResp.isLast()){
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            if (mCurrentPage == 1) {
                mRefreshLayout.finishRefresh();
                mAdapter.setNewData(listResp.getData());
                mAdapter.setEmptyView(R.layout.empty_data, (ViewGroup) mRecyclerView.getParent());
            } else {
                mAdapter.addData(listResp.getData());
                mRefreshLayout.finishLoadMore();
            }
        }
    }
}
