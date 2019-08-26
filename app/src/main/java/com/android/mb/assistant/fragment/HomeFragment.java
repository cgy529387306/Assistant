package com.android.mb.assistant.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.MsgDetailActivity;
import com.android.mb.assistant.activity.competitive.CompetitiveBrowseActivity;
import com.android.mb.assistant.activity.competitive.CompetitiveInputActivity;
import com.android.mb.assistant.activity.goods.ApplyDetailsActivity;
import com.android.mb.assistant.activity.goods.ApplyListActivity;
import com.android.mb.assistant.activity.goods.GoodsBrowseActivity;
import com.android.mb.assistant.activity.goods.GoodsInputActivity;
import com.android.mb.assistant.adapter.HomeMsgAdapter;
import com.android.mb.assistant.adapter.ITypeAdapter;
import com.android.mb.assistant.base.BaseMvpFragment;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.BannerBean;
import com.android.mb.assistant.entitys.GoodsListResp;
import com.android.mb.assistant.entitys.HomeData;
import com.android.mb.assistant.entitys.IType;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.ImageUtils;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.NestedGridView;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 首页
 * Created by cgy on 16/7/18.
 */
public class HomeFragment extends BaseMvpFragment<CommonPresenter, ICommonView> implements ICommonView, OnRefreshListener, OnLoadMoreListener {

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
        mBanner = header.findViewById(R.id.bannerView);
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
        getDataFromServer();
    }

    @Override
    protected void setListener() {
        mHomeMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("mNoticeBean",mHomeMsgAdapter.getItem(position));
                NavigationHelper.startActivity(getActivity(), MsgDetailActivity.class, bundle, false);
            }
        });
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
        getDataFromServer();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
                .keyboardEnable(false)
                .init();
    }

    private void getDataFromServer(){
        Map<String,String> requestParams = new HashMap<>();
        mPresenter.requestManage(CodeConstants.KEY_EXTRA_DATA,requestParams,false);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        mRefreshLayout.finishRefresh();
        HomeData homeData = JsonHelper.fromJson(result,HomeData.class);
        if (homeData!=null){
            if (homeData.isSuccess()){
                if (Helper.isNotEmpty(homeData.getBannerList())){
                    mBanner.setImageLoader(new GlideImageLoader());
                    mBanner.setImages(homeData.getBannerList());
                    mBanner.start();
                }
                if (Helper.isNotEmpty(homeData.getBulletinList())){
                    mHomeMsgAdapter.setNewData(homeData.getBulletinList());
                }
            }else{
                showToastMessage(homeData.getMessage());
            }

        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageUtils.displayImage(getActivity(),((BannerBean)path).getBannerPicUrl(),imageView);
        }
    }


}
