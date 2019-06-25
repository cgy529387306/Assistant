package com.android.mb.assistant.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveBrowseActivity;
import com.android.mb.assistant.activity.competitive.CompetitiveInputActivity;
import com.android.mb.assistant.activity.goods.GoodsBrowseActivity;
import com.android.mb.assistant.activity.goods.GoodsInputActivity;
import com.android.mb.assistant.adapter.HomeMsgAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.HomePresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.IHomeView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 * Created by cgy on 16/7/18.
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter, IHomeView> implements IHomeView, OnRefreshListener, OnLoadMoreListener {

    private HomeMsgAdapter mHomeMsgAdapter;
    private RecyclerView mRecyclerView;
    private NestedGridView mGridCate;
    private Banner mBanner;
    private SmartRefreshLayout mRefreshLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
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
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, 1,getResources().getColor(R.color.gray_divider)));
        mHomeMsgAdapter = new HomeMsgAdapter(new ArrayList());
        mRecyclerView.setAdapter(mHomeMsgAdapter);
        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header, mRecyclerView, false);
        mGridCate = header.findViewById(R.id.gridCate);
//        mBanner = header.findViewById(R.id.bannerView);
        mHomeMsgAdapter.addHeaderView(header);

        mGridCate.setAdapter(new ITypeAdapter(getActivity(),getHomeTypeList()));
        mGridCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //竞情录入
                    NavigationHelper.startActivity(getActivity(), CompetitiveInputActivity.class,null,false);
                }else if (i == 1){
                    //竞情浏览
                    NavigationHelper.startActivity(getActivity(), CompetitiveBrowseActivity.class,null,false);
                }else if (i == 2){
                    //物资录入
                    NavigationHelper.startActivity(getActivity(), GoodsInputActivity.class,null,false);
                }else if (i == 3){
                    //物资浏览
                    NavigationHelper.startActivity(getActivity(), GoodsBrowseActivity.class,null,false);
                }
            }
        });
    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    private List<IType> getHomeTypeList(){
        List<IType> homeTypeList = new ArrayList<>();
        homeTypeList.add(new IType(R.mipmap.icon_luru,"竞情录入"));
        homeTypeList.add(new IType(R.mipmap.icon_all,"全部竞情"));
        homeTypeList.add(new IType(R.mipmap.icon_wuzi,"物资录入"));
        homeTypeList.add(new IType(R.mipmap.icon_liulan,"物资浏览"));
        return homeTypeList;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
                .keyboardEnable(false)
                .navigationBarColor(R.color.colorPrimary)
                .init();
    }
}
