package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsBrowseAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 物资购物车
 */
public class GoodsShoppingCartActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

    private GoodsBrowseAdapter mGoodsBrowseAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_shopping_cart;
    }

    @Override
    protected void initTitle() {
        setTitleText("物资购物车");
        setRightImage(R.mipmap.icon_edit);
    }

    @Override
    protected void bindViews() {
        initView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
    }

    private void initView() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mGoodsBrowseAdapter = new GoodsBrowseAdapter(getList());
        mRecyclerView.setAdapter(mGoodsBrowseAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("联想电脑");
        list.add("苹果电脑");
        list.add("华硕电脑");
        list.add("联想电脑");
        list.add("苹果电脑");
        list.add("华硕电脑");
        return list;
    }
}
