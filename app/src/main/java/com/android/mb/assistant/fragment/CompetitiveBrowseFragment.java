package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveDetailsActivity;
import com.android.mb.assistant.adapter.CompetitiveOrderAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CompetitiveListResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.rxbus.Events;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
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

import rx.functions.Action1;


/**
 * 竞情查看
 * Created by cgy on 16/7/18.
 */
public class CompetitiveBrowseFragment extends BaseMvpFragment<CommonPresenter,ICommonView> implements ICommonView,BaseQuickAdapter.OnItemClickListener,OnRefreshListener, OnLoadMoreListener {

    private CompetitiveOrderAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    /**
     * 1：未派单2：已派单3:反抢成功4:反抢失败 5：未读竞情 99:查询全部
     */
    private static final String KEY_STATE = "key_state";

    private int mState = 99;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_chapter_approval;
    }

    /**
     * @param state
     * @return
     */
    public static Fragment getInstance(int state) {
        Fragment fragment = new CompetitiveBrowseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_STATE, state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        initView(view);
    }

    private void initView(View view) {
        mState = getArguments().getInt(KEY_STATE,99);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(true);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new CompetitiveOrderAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void processLogic() {
        onRefresh(null);
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("cOpName",CurrentUser.getInstance().getUname());
        requestParams.put("status",String.valueOf(mState));
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_LIST,requestParams,false);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
        regiestEvent(ProjectConstants.EVENT_UPDATE_COMPETITIVE, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                getListFormServer();
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
    public void requestSuccess(String result) {
        CompetitiveListResp listResp = JsonHelper.fromJson(result,CompetitiveListResp.class);
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
        
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cometitive",mAdapter.getItem(position));
        NavigationHelper.startActivity(getActivity(), CompetitiveDetailsActivity.class,bundle,false);
    }
}
