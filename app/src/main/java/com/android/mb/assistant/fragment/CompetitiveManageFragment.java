package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveBrowseActivity;
import com.android.mb.assistant.activity.competitive.CompetitiveInputActivity;
import com.android.mb.assistant.adapter.CompetitiveOrderAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CompetitiveListResp;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 竞情管理
 * Created by cgy on 16/7/18.
 */
public class CompetitiveManageFragment extends BaseMvpFragment<CommonPresenter, ICommonView> implements ICommonView, OnRefreshListener, OnLoadMoreListener {

    private CompetitiveOrderAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private NestedGridView mGridCate;
    private SmartRefreshLayout mRefreshLayout;
    private TextView mTvTitle;
    private ImageView mTvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_competitive_manage;
    }

    @Override
    protected void bindViews(View view) {
        initView(view);
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new CompetitiveOrderAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_competitive_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
        mAdapter.addHeaderView(header);

        mTvTitle = view.findViewById(R.id.tv_title);
        mTvBack = view.findViewById(R.id.iv_back);
        mTvBack.setVisibility(View.GONE);
        mTvTitle.setText("竞情管理");
        mGridCate.setAdapter(new ITypeAdapter(getActivity(),getCompetitiveTypeList()));
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 7) {
                    //竞情录入
                    NavigationHelper.startActivity(getActivity(), CompetitiveInputActivity.class,null,false);
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
                    NavigationHelper.startActivity(getActivity(), CompetitiveBrowseActivity.class,bundle,false);
                }
            }
        });
    }

    @Override
    protected void processLogic() {
        onRefresh(null);
    }

    @Override
    protected void setListener() {
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
        mPresenter.requestManage(CodeConstants.KEY_COMPETITIVE_MANAGE,requestParams,false);
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
    public void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true).init();
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
}
