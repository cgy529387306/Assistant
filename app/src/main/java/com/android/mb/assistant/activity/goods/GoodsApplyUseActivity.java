package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.SelectPersonActivity;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CartBean;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用申请
 */
public class GoodsApplyUseActivity  extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener{

    private EditText mEtName,mEtTel,mEtRemark;
    private TextView mSelectApproval;
    private TextView mTvTime;
    private RecyclerView mRecyclerView;
    private GoodsApplyUseAdapter mGoodsApplyUseAdapter;
    private TextView mTvSubmit;
    private List<CartBean> mCartBeanList = new ArrayList<>();

    @Override
    protected void loadIntent() {
        mCartBeanList = (List<CartBean>) getIntent().getSerializableExtra("dataList");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_apply_use;
    }

    @Override
    protected void initTitle() {
        setTitleText("使用申请");
    }

    @Override
    protected void bindViews() {
        mEtName = findViewById(R.id.et_name);
        mEtTel = findViewById(R.id.et_tel);
        mEtRemark = findViewById(R.id.et_remark);
        mTvTime = findViewById(R.id.tv_time);
        mSelectApproval = findViewById(R.id.tv_select_approval);
        mTvSubmit = findViewById(R.id.tv_submit);
        mRecyclerView = findViewById(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL, AppHelper.calDpi2px(10),getResources().getColor(R.color.list_divider)));
        mGoodsApplyUseAdapter = new GoodsApplyUseAdapter(mCartBeanList);
        mRecyclerView.setAdapter(mGoodsApplyUseAdapter);
        mTvTime.setText(Helper.date2String(new Date(),Helper.DATE_FORMAT1));
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mSelectApproval.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_submit){
            doSubmit();
        }else if (id == R.id.tv_select_approval){
            NavigationHelper.startActivity(this, SelectApproverActivity.class,null,false);
        }
    }

    @Override
    public void requestSuccess(String requestCode, String result) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void doSubmit(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(mPageSize));
        mPresenter.requestCart(CodeConstants.KEY_CART_APPLY,requestParams,true);
    }
}
