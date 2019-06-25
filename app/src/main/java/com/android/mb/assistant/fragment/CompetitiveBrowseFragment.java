package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveDetailsActivity;
import com.android.mb.assistant.activity.goods.GoodsDetailsActivity;
import com.android.mb.assistant.adapter.CompetitiveOrderAdapter;
import com.android.mb.assistant.adapter.GoodsBrowseAdapter;
import com.android.mb.assistant.base.BaseFragment;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 竞情查看
 * Created by cgy on 16/7/18.
 */
public class CompetitiveBrowseFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private CompetitiveOrderAdapter mCompetitiveOrderAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private static final String KEY_STATE = "key_state";

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
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mCompetitiveOrderAdapter = new CompetitiveOrderAdapter(getList());
        mRecyclerView.setAdapter(mCompetitiveOrderAdapter);
        mCompetitiveOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavigationHelper.startActivity(getActivity(), CompetitiveDetailsActivity.class,null,false);
            }
        });
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("陈秋梅");
        list.add("蔡桂有");
        list.add("陈秋梅");
        list.add("蔡桂有");
        return list;
    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void initImmersionBar() {

    }
}
