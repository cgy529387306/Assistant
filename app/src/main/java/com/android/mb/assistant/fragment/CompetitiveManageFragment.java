package com.android.mb.assistant.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.CompetitiveOrderAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.CompetitivePresenter;
import com.android.mb.assistant.view.interfaces.ICompetitiveView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 竞情管理
 * Created by cgy on 16/7/18.
 */
public class CompetitiveManageFragment extends BaseMvpFragment<CompetitivePresenter, ICompetitiveView> implements ICompetitiveView, OnRefreshListener, OnLoadMoreListener {

    private CompetitiveOrderAdapter mCompetitiveOrderAdapter;
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
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mCompetitiveOrderAdapter = new CompetitiveOrderAdapter(getList());
        mRecyclerView.setAdapter(mCompetitiveOrderAdapter);
        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_competitive_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
        mCompetitiveOrderAdapter.addHeaderView(header);

        mTvTitle = view.findViewById(R.id.tv_title);
        mTvBack = view.findViewById(R.id.iv_back);
        mTvBack.setVisibility(View.GONE);
        mTvTitle.setText("竞情管理");
        mGridCate.setAdapter(new ITypeAdapter(getActivity(),getCompetitiveTypeList()));
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {}
            }
        });
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
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
    protected CompetitivePresenter createPresenter() {
        return new CompetitivePresenter();
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getList();

    }
}
