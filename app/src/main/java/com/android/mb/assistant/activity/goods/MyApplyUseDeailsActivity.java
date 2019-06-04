package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的使用申请
 */
public class MyApplyUseDeailsActivity extends BaseActivity{
    private RecyclerView mRecyclerView;
    private GoodsApplyUseAdapter mGoodsApplyUseAdapter;
    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_apply_use_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("我的使用申请");
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
        mRecyclerView = findViewById(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mGoodsApplyUseAdapter = new GoodsApplyUseAdapter(getList());
        mRecyclerView.setAdapter(mGoodsApplyUseAdapter);
    }
    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("苹果电脑");
        list.add("华硕电脑");
        return list;
    }
}
