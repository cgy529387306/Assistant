package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.MainActivity;
import com.android.mb.assistant.adapter.PersonAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.LoginResp;
import com.android.mb.assistant.entitys.PersonListResp;
import com.android.mb.assistant.entitys.UserBean;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.MACHelper;
import com.android.mb.assistant.utils.NavigationHelper;
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
 * 选择人员
 */
public class SelectPersonActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, BaseQuickAdapter.OnItemClickListener, OnRefreshListener, OnLoadMoreListener {

    private PersonAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private String mCompetitiveId;
    @Override
    protected void loadIntent() {
        mCompetitiveId = getIntent().getStringExtra("competitiveId");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_recycleview;
    }

    @Override
    protected void initTitle() {
        setTitleText("选择人员");
    }

    @Override
    protected void bindViews() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(true);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(1),getResources().getColor(R.color.list_divider)));
        mAdapter = new PersonAdapter(new ArrayList());
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
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("uName",CurrentUser.getInstance().getUname());
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DISPATCHERS,requestParams,false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        UserBean userBean = mAdapter.getItem(position);
        new MaterialDialog.Builder(this)
                .title("派单")
                .content("确认派单给"+userBean.getUname()+"吗？")
                .positiveText("确认")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        doSubmit(userBean);
                    }
                })
                .show();
    }

    private void doSubmit(UserBean userBean){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("cId",mCompetitiveId);
        requestParams.put("mUid",CurrentUser.getInstance().getMuid());
        requestParams.put("authority",String.valueOf(CurrentUser.getInstance().getAuthority()));
        requestParams.put("receiveId",userBean.getMuid());
        requestParams.put("receiveName",userBean.getUname());
        requestParams.put("department",userBean.getUid());
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DISPATCH,requestParams,true);
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
        if (CodeConstants.KEY_COMPETITIVE_DISPATCHERS.equals(requestCode)){
            PersonListResp listResp = JsonHelper.fromJson(result,PersonListResp.class);
            if (listResp!=null){
                if (mCurrentPage == 1) {
                    mRefreshLayout.finishRefresh();
                    mAdapter.setNewData(listResp.getData());
                    mAdapter.setEmptyView(R.layout.empty_data, (ViewGroup) mRecyclerView.getParent());
                } else {
                    if (Helper.isEmpty(result)) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        mAdapter.addData(listResp.getData());
                        mRefreshLayout.finishLoadMore();
                    }
                }
            }
        }else if (CodeConstants.KEY_COMPETITIVE_DISPATCH.equals(requestCode)){
            CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
            if (resp!=null){
                if (resp.isSuccess()){
                    showToastMessage("派单成功");
                    finish();
                }else{
                    showToastMessage(resp.getMessage());
                }
            }
        }

    }
}
