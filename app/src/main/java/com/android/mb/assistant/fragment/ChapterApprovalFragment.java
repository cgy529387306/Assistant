package com.android.mb.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveBrowseActivity;
import com.android.mb.assistant.activity.goods.ChapterApprovalDeailsActivity;
import com.android.mb.assistant.activity.goods.MyApplyUseDeailsActivity;
import com.android.mb.assistant.adapter.IGoodsManageAdapter;
import com.android.mb.assistant.base.BaseFragment;
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
 * 用章审批
 * Created by cgy on 16/7/18.
 */
public class ChapterApprovalFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private IGoodsManageAdapter mIGoodsmanageAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private static final String KEY_STATE = "key_state";
    private static final String KEY_TYPE = "key_type";
    private int status;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_chapter_approval;
    }

    /**
     * @param state 0:今天 1:明天 2:全部
     * @return
     */
    public static Fragment getInstance(int state,int type) {
        Fragment fragment = new ChapterApprovalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_STATE, state);
        bundle.putInt(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        initView(view);
    }

    private void initView(View view) {
        status = (int) getArguments().get(KEY_STATE);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mIGoodsmanageAdapter = new IGoodsManageAdapter(getList());
        mRecyclerView.setAdapter(mIGoodsmanageAdapter);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("张大大的物资申请");
        list.add("张大大的物资申请");
        list.add("张大大的物资申请");
        return list;
    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
        mIGoodsmanageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (status == 0) { //用章审批
                    NavigationHelper.startActivity(getActivity(), ChapterApprovalDeailsActivity.class, null, false);
                }else if (status == 1){  // 我的使用申请
                    NavigationHelper.startActivity(getActivity(), MyApplyUseDeailsActivity.class, null, false);

                }
            }
        });
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
