package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveDetailsActivity;
import com.android.mb.assistant.adapter.GoodsBrowseAdapter;
import com.android.mb.assistant.adapter.GoodsShoppingCartAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.ShoppingCartData;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
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

/**
 * 物资购物车
 */
public class GoodsShoppingCartActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

    private GoodsShoppingCartAdapter mGoodsShoppingCartAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private TextView mTvApplyUse;

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
        getListFormServer();
    }

    @Override
    protected void setListener() {
        mTvApplyUse.setOnClickListener(this);
        mGoodsShoppingCartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingCartData data = mGoodsShoppingCartAdapter.getItem(position);
                data.setSelect(!data.isSelect());
                mGoodsShoppingCartAdapter.setData(position,data);
            }
        });
    }

    private void initView() {
        mTvApplyUse = findViewById(R.id.tv_apply_use);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mGoodsShoppingCartAdapter = new GoodsShoppingCartAdapter(getList());
        mRecyclerView.setAdapter(mGoodsShoppingCartAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_apply_use){
            NavigationHelper.startActivity(this, GoodsApplyUseActivity.class,null,false);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
    private List<ShoppingCartData> getList(){
        List<ShoppingCartData> list = new ArrayList<>();
        ShoppingCartData data = new ShoppingCartData();
        data.setName("联想电脑");
        data.setSelect(false);
        list.add(data);
        ShoppingCartData data1 = new ShoppingCartData();
        data.setName("苹果电脑");
        data.setSelect(false);
        list.add(data1);
        ShoppingCartData data2 = new ShoppingCartData();
        data.setName("华硕电脑");
        data.setSelect(false);
        list.add(data2);
        return list;
    }

    @Override
    public void requestSuccess(String requestCode, String result) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
//        requestParams.put("page",String.valueOf(mCurrentPage));
//        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestCart(CodeConstants.KEY_CART_LIST,requestParams,false);
    }
}
