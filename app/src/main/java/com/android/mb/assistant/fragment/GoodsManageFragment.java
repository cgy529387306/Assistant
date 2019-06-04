package com.android.mb.assistant.fragment;

import android.media.Image;
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
import com.android.mb.assistant.activity.goods.ChapterApprovalActivity;
import com.android.mb.assistant.activity.goods.ChapterApprovalDeailsActivity;
import com.android.mb.assistant.activity.goods.GoodsBrowseActivity;
import com.android.mb.assistant.activity.goods.GoodsInputActivity;
import com.android.mb.assistant.activity.goods.GoodsShoppingCartActivity;
import com.android.mb.assistant.adapter.GoodsManageAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.GoodsPresenter;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.IGoodsView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 物资管理
 * Created by cgy on 16/7/18.
 */
public class GoodsManageFragment extends BaseMvpFragment<GoodsPresenter, IGoodsView> implements IGoodsView, OnRefreshListener, OnLoadMoreListener, GoodsManageAdapter.OnChapterApprovalListener {
    private GoodsManageAdapter mGoodsmanageAdapter;
    private RecyclerView mRecyclerView;
    private NestedGridView mGridCate;
    private SmartRefreshLayout mRefreshLayout;
    private TextView mTvTitle;
    private ImageView mTvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_goods_manage;
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
        mGoodsmanageAdapter = new GoodsManageAdapter(getList(),this);
        mRecyclerView.setAdapter(mGoodsmanageAdapter);
        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_goods_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
        mGoodsmanageAdapter.addHeaderView(header);

        mTvTitle = view.findViewById(R.id.tv_title);
        mTvBack = view.findViewById(R.id.iv_back);
        mTvTitle.setText("物资管理");
        mTvBack.setVisibility(View.GONE);
        mGridCate.setAdapter(new ITypeAdapter(getActivity(),getGoodsTypeList()));
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {//物资录入
                    NavigationHelper.startActivity(getActivity(), GoodsInputActivity.class,null,false);
                }else if (i == 1) {//物资浏览
                    NavigationHelper.startActivity(getActivity(), GoodsBrowseActivity.class,null,false);
                }
                else if (i == 3) {//物资购物车
                    NavigationHelper.startActivity(getActivity(), GoodsShoppingCartActivity.class,null,false);
                }
            }
        });
        mGoodsmanageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("status",position);
                NavigationHelper.startActivity(getActivity(), ChapterApprovalActivity.class,bundle,false);
//                if (position == 0){  // 用章审批
//                    NavigationHelper.startActivity(getActivity(), ChapterApprovalActivity.class,null,false);
//                }else if (position == 1){  // 我的申请
//                    NavigationHelper.startActivity(getActivity(), ChapterApprovalActivity.class,null,false);
//                }else if (position == 2){  // 最新入库
//
//                }
            }
        });
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("用章审批");
        list.add("我的申请");
        list.add("最新入库");
        return list;

    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    private List<IType> getGoodsTypeList(){
        List<IType> competitiveTypeList = new ArrayList<>();
        competitiveTypeList.add(new IType(R.mipmap.icon_wuzi,"物资录入"));
        competitiveTypeList.add(new IType(R.mipmap.icon_liulan,"物资浏览"));
        competitiveTypeList.add(new IType(R.mipmap.icon_wode_lingyong,"我的领用"));
        competitiveTypeList.add(new IType(R.mipmap.icon_gowuche,"物资购物车"));
        return competitiveTypeList;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getList();

    }

    @Override
    public void onGoodsApproval() {
        NavigationHelper.startActivity(getActivity(), ChapterApprovalDeailsActivity.class,null,false);
    }
}
