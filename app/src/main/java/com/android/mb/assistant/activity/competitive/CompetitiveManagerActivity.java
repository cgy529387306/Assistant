package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.CompetitiveOrderAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CompetitiveListResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.rxbus.Events;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * 竞情管理
 */
public class CompetitiveManagerActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, OnRefreshListener, OnLoadMoreListener ,BaseQuickAdapter.OnItemClickListener{
    private CompetitiveOrderAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private NestedGridView mGridCate;
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
        setTitleText("竞情管理");
    }

    @Override
    protected void bindViews() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new CompetitiveOrderAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
        //添加Header
        View header = LayoutInflater.from(this).inflate(R.layout.item_competitive_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
        mAdapter.addHeaderView(header);
        mGridCate.setAdapter(new ITypeAdapter(this,getCompetitiveTypeList()));
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 7) {
                    //竞情录入
                    NavigationHelper.startActivity(CompetitiveManagerActivity.this, CompetitiveInputActivity.class,null,false);
                }else{
                    //竞情浏览
                    Bundle bundle = new Bundle();
                    if (position==0){
                        //未读竞情
                        bundle.putInt("page",6);
                    }else if (position==6){
                        //全部竞情
                        bundle.putInt("page",0);
                    }else{
                        bundle.putInt("page",position);
                    }
                    NavigationHelper.startActivity(CompetitiveManagerActivity.this, CompetitiveBrowseActivity.class,bundle,false);
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        onRefresh(null);
    }


    @Override
    protected void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
        registerEvent(ProjectConstants.EVENT_UPDATE_COMPETITIVE, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                onRefresh(null);
            }
        });
    }


    private List<IType> getCompetitiveTypeList(){
        List<IType> competitiveTypeList = new ArrayList<>();
        competitiveTypeList.add(new IType(R.mipmap.icon_weidu,"未读竞情"));
        competitiveTypeList.add(new IType(R.mipmap.icon_weipaidan,"未派单"));
        competitiveTypeList.add(new IType(R.mipmap.icon_yipaidan,"已派单"));
        competitiveTypeList.add(new IType(R.mipmap.icon_wode,"我的工单"));
        competitiveTypeList.add(new IType(R.mipmap.icon_chenggong,"反抢成功"));
        competitiveTypeList.add(new IType(R.mipmap.icon_shibai,"反抢失败"));
        competitiveTypeList.add(new IType(R.mipmap.icon_all,"全部竞情"));
        competitiveTypeList.add(new IType(R.mipmap.icon_luru,"竞情录入"));
        return competitiveTypeList;
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("cOpName",CurrentUser.getInstance().getUname());
        requestParams.put("status",String.valueOf(99));
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_LIST,requestParams,false);
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
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        CompetitiveListResp listResp = JsonHelper.fromJson(result,CompetitiveListResp.class);
        if (listResp!=null){
            if (listResp.isLast()){
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            if (mCurrentPage == 1) {
                mRefreshLayout.finishRefresh();
                mAdapter.setNewData(listResp.getData());
            } else {
                mAdapter.addData(listResp.getData());
                mRefreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("competitive",mAdapter.getItem(position));
        NavigationHelper.startActivity(CompetitiveManagerActivity.this, CompetitiveDetailsActivity.class,bundle,false);
    }

}
