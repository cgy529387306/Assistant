package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.goods.GoodsDetailsActivity;
import com.android.mb.assistant.activity.goods.GoodsShoppingCartActivity;
import com.android.mb.assistant.adapter.GoodsBrowseAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.entitys.GoodsListResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.rxbus.Events;
import com.android.mb.assistant.utils.AppHelper;
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
 * 物资浏览
 * Created by cgy on 16/7/18.
 */
public class GoodsBrowseFragment extends BaseMvpFragment<CommonPresenter, ICommonView> implements ICommonView,BaseQuickAdapter.OnItemClickListener,OnRefreshListener, OnLoadMoreListener ,GoodsBrowseAdapter.OnItemOperateListener{

    private GoodsBrowseAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private static final String KEY_STATE = "key_state";
    private String mCategory;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_chapter_approval;
    }

    /**
     * @return
     */
    public static Fragment getInstance(String category) {
        Fragment fragment = new GoodsBrowseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STATE, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        initView(view);
    }

    private void initView(View view) {
        mCategory = getArguments().getString(KEY_STATE);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(true);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new GoodsBrowseAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic() {
        onRefresh(null);
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("type",mCategory);
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestData(CodeConstants.KEY_GOODS_LIST,requestParams,false);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setItemOperateListener(this);
        registerEvent(ProjectConstants.EVENT_UPDATE_GOODS, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                onRefresh(null);
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
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode,String result) {
        if (CodeConstants.KEY_CART_ADD.equals(requestCode)){
            CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
            if (resp!=null){
                if (resp.isSuccess()){
                    showToastMessage("添加购物车成功!");
                    NavigationHelper.startActivity(getActivity(), GoodsShoppingCartActivity.class,null,false);
                }else{
                    showToastMessage(resp.getMessage());
                }
            }
        }else{
            GoodsListResp listResp = JsonHelper.fromJson(result,GoodsListResp.class);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",mAdapter.getItem(position));
        NavigationHelper.startActivity(getActivity(), GoodsDetailsActivity.class,bundle,false);
    }

    @Override
    public void onApply(GoodsBean item) {
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("materialId",item.getMaterialId());
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("materialNum",String.valueOf(item.getAddMum()));
        mPresenter.requestCart(CodeConstants.KEY_CART_ADD,requestParams,false);
    }
}
