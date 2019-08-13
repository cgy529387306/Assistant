package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveDetailsActivity;
import com.android.mb.assistant.adapter.GoodsBrowseAdapter;
import com.android.mb.assistant.adapter.GoodsShoppingCartAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CartBean;
import com.android.mb.assistant.entitys.CartListResp;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.GoodsListResp;
import com.android.mb.assistant.entitys.ShoppingCartData;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
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

    private GoodsShoppingCartAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private TextView mTvApplyUse;
    private AppCompatCheckBox mCbAll;
    private TextView mTvAll;

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
    }

    @Override
    protected void bindViews() {
        mTvApplyUse = findViewById(R.id.tv_apply_use);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new GoodsShoppingCartAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
        mCbAll = findViewById(R.id.cb_all);
        mTvAll = findViewById(R.id.tv_all);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        onRefresh(null);
    }

    @Override
    protected void setListener() {
        mTvApplyUse.setOnClickListener(this);
        mAdapter.setItemOperateListener(new GoodsShoppingCartAdapter.OnItemOperateListener() {
            @Override
            public void plusNum(CartBean item) {
                doPlus(item);
            }

            @Override
            public void minusNum(CartBean item) {
                doMinus(item);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CartBean cartBean = mAdapter.getItem(position);
                cartBean.setSelect(!cartBean.isSelect());
                mAdapter.setData(position,cartBean);
            }
        });
        mCbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mAdapter.setIsAllCheck(b);
            }
        });
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
        if (CodeConstants.KEY_CART_MINUS.equals(requestCode)){
            CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
            if (resp!=null){
                if (resp.isSuccess()){
                    onRefresh(null);
                }else{
                    showToastMessage(resp.getMessage());
                }
            }
        }else{
            CartListResp listResp = JsonHelper.fromJson(result,CartListResp.class);
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

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        mPresenter.requestCart(CodeConstants.KEY_CART_LIST,requestParams,false);
    }

    private void doPlus(CartBean item){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("materialId",item.getMaterialId());
        requestParams.put("type","0");
        mPresenter.requestCart(CodeConstants.KEY_CART_MINUS,requestParams,false);
    }

    private void doMinus(CartBean item){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("materialId",item.getMaterialId());
        requestParams.put("type","1");
        mPresenter.requestCart(CodeConstants.KEY_CART_MINUS,requestParams,false);
    }
}
