package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.goods.ApplyDetailsActivity;
import com.android.mb.assistant.adapter.IGoodsManageAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.ApplyListResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.JsonHelper;
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
 * 用章审批
 * Created by cgy on 16/7/18.
 */
public class ApplyFragment extends BaseMvpFragment<CommonPresenter,ICommonView> implements ICommonView, OnRefreshListener, OnLoadMoreListener {

    private IGoodsManageAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private static final String KEY_STATE = "key_state";
    private static final String KEY_TYPE = "key_type";
    private int mType;//0:我的审批  1:我的申请  2:最新入库
    private int mState;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_chapter_approval;
    }

    /**
     * @param state 0:全部 1:未通过 2:通过
     * @return
     */
    public static Fragment getInstance(int type,int state) {
        Fragment fragment = new ApplyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        bundle.putInt(KEY_STATE, state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        initView(view);
    }

    private void initView(View view) {
        mState = (int) getArguments().get(KEY_STATE);
        mType = (int) getArguments().get(KEY_TYPE);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.list_divider)));
        mAdapter = new IGoodsManageAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic() {
        onRefresh(null);
    }


    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        if (mState==1){
            requestParams.put("apSyStatus", "1");
        }else if (mState==2){
            requestParams.put("apSyStatus", "0");
        }else{
            requestParams.put("apSyStatus", "");
        }
        if (mType == 0){
            mPresenter.requestApplicant(CodeConstants.KEY_GOODS_APPLICANT,requestParams,false);
        }else if (mType == 1){
            mPresenter.requestApplicant(CodeConstants.KEY_GOODS_APPLY_LIST,requestParams,false);
        }
    }

    @Override
    protected void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("mApplyBean",mAdapter.getItem(position));
                NavigationHelper.startActivity(getActivity(), ApplyDetailsActivity.class, bundle, false);
            }
        });
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

    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        ApplyListResp listResp = JsonHelper.fromJson(result,ApplyListResp.class);
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

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }
}
