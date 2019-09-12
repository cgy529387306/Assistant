package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsManageAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.GoodsManage;
import com.android.mb.assistant.entitys.GoodsManageResp;
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
 * 物资管理
 */
public class GoodsManagerActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, OnRefreshListener, OnLoadMoreListener, GoodsManageAdapter.OnChapterApprovalListener {
    private GoodsManageAdapter mAdapter;
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
        setTitleText("物资管理");
    }

    @Override
    protected void bindViews() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mAdapter = new GoodsManageAdapter(new ArrayList(),this);
        mRecyclerView.setAdapter(mAdapter);
        //添加Header
        View header = LayoutInflater.from(this).inflate(R.layout.item_goods_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
        mAdapter.addHeaderView(header);
        mGridCate.setAdapter(new ITypeAdapter(this,getGoodsTypeList()));
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        onRefresh(null);
    }


    @Override
    protected void setListener() {
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {//物资录入
                    NavigationHelper.startActivity(GoodsManagerActivity.this, GoodsInputActivity.class,null,false);
                }else if (i == 1) {//物资浏览
                    NavigationHelper.startActivity(GoodsManagerActivity.this, GoodsBrowseActivity.class,null,false);
                }else if (i == 2) {//我的领用
                    Bundle bundle = new Bundle();
                    bundle.putInt("type",1);
                    NavigationHelper.startActivity(GoodsManagerActivity.this, ApplyListActivity.class,bundle,false);
                } else if (i == 3) {//物资购物车
                    NavigationHelper.startActivity(GoodsManagerActivity.this, GoodsShoppingCartActivity.class,null,false);
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //0:用章审批 1:我的申请 2:我的入库
                if (position==2){
                    NavigationHelper.startActivity(GoodsManagerActivity.this, GoodsBrowseActivity.class,null,false);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putInt("type",position);
                    NavigationHelper.startActivity(GoodsManagerActivity.this, ApplyListActivity.class,bundle,false);
                }
            }
        });
        registerEvent(ProjectConstants.EVENT_UPDATE_GOODS, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                onRefresh(null);
            }
        });
        registerEvent(ProjectConstants.EVENT_UPDATE_APPLY, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                onRefresh(null);
            }
        });
        registerEvent(ProjectConstants.EVENT_APPLY_SUCCESS, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                onRefresh(null);
            }
        });
    }

    private List<IType> getGoodsTypeList(){
        List<IType> competitiveTypeList = new ArrayList<>();
        competitiveTypeList.add(new IType(R.mipmap.icon_wuzi,"物资录入"));
        competitiveTypeList.add(new IType(R.mipmap.icon_liulan,"物资浏览"));
        competitiveTypeList.add(new IType(R.mipmap.icon_wode_lingyong,"我的领用"));
        competitiveTypeList.add(new IType(R.mipmap.icon_gowuche,"物资购物车"));
        return competitiveTypeList;
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        requestParams.put("apApproverId", CurrentUser.getInstance().getMuid());
        mPresenter.requestApplicant(CodeConstants.KEY_GOODS_MANAGE,requestParams,false);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        getListFormServer();
    }

    @Override
    public void onGoodsApproval(int type, ApplyBean applyBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        bundle.putSerializable("mApplyBean",applyBean);
        NavigationHelper.startActivity(GoodsManagerActivity.this, ApplyDetailsActivity.class, bundle, false);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        mRefreshLayout.finishRefresh();
        GoodsManageResp listResp = JsonHelper.fromJson(result,GoodsManageResp.class);
        if (listResp!=null){
            if (listResp.isSuccess()){
                List<GoodsManage> dataList = new ArrayList<>();
                dataList.add(new GoodsManage("用章审批",listResp.getSpList(),listResp.getZxList()));
                dataList.add(new GoodsManage("我的申请",listResp.getSqList(),listResp.getZxList()));
                dataList.add(new GoodsManage("最新入库",listResp.getSqList(),listResp.getZxList()));
                mAdapter.setNewData(dataList);
            }else {
                showToastMessage(listResp.getMessage());
            }
        }
    }
}
