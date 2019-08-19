package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.entitys.ApplyDetailResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 用章审批详情
 */
public class ApplyDetailsActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener{

    private TextView mTvName,mTvTel,mTvTime,mTvRemark;
    private TextView mTvReturn,mTvRefuse,mTvAgree,mTvRevoke;
    private LinearLayout mLlOperate;
    private RecyclerView mRecyclerView;
    private GoodsApplyUseAdapter mAdapter;
    private ApplyBean mApplyBean;
    @Override
    protected void loadIntent() {
        mApplyBean = (ApplyBean) getIntent().getSerializableExtra("mApplyBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_details;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void bindViews() {
        mTvName = findViewById(R.id.tv_name);
        mTvTel = findViewById(R.id.tv_tel);
        mTvTime = findViewById(R.id.tv_time);
        mTvRemark = findViewById(R.id.tv_remark);
        mTvReturn = findViewById(R.id.tv_return);
        mTvRefuse = findViewById(R.id.tv_refuse);
        mTvAgree = findViewById(R.id.tv_agree);
        mTvRevoke = findViewById(R.id.tv_revoke);
        mLlOperate = findViewById(R.id.lly_operate);
//        mTvRevoke.setVisibility(mType==1||mType==2?View.VISIBLE:View.GONE);
//        mLlOperate.setVisibility(mType==1||mType==2?View.GONE:View.VISIBLE);
        mRecyclerView = findViewById(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mAdapter = new GoodsApplyUseAdapter(new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initData();
        getDetail();
    }

    @Override
    protected void setListener() {
        mTvReturn.setOnClickListener(this);
        mTvRefuse.setOnClickListener(this);
        mTvAgree.setOnClickListener(this);
        mTvRevoke.setOnClickListener(this);
    }

    private void initData(){
        if (mApplyBean!=null){
            mTvName.setText(mApplyBean.getApSqName());
            mTvTel.setText(mApplyBean.getApTel());
            mTvTime.setText(mApplyBean.getApTime());
            mTvRemark.setText(mApplyBean.getApRemarks());
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_return){
            //退回
        }else if (id == R.id.tv_refuse){
            //拒绝
        }else if (id == R.id.tv_agree){
            //同意
        }else if (id == R.id.tv_revoke){
            //撤回
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        ApplyDetailResp resp = JsonHelper.fromJson(result,ApplyDetailResp.class);
        if (resp!=null){
            if (resp.isSuccess()){
                mApplyBean = resp.getData();
                initData();
                if (Helper.isNotEmpty(resp.getRows())){
                    mAdapter.setNewData(resp.getRows());
                }
            }else{
                showToastMessage(resp.getMessage());
            }
        }
    }

    private void getDetail(){
        if (mApplyBean!=null){
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("mUid", mApplyBean.getApSqId());
            requestParams.put("applicationId",mApplyBean.getApplicationId());
            mPresenter.requestApplicant(CodeConstants.KEY_GOODS_APPLY_DETAIL,requestParams,false);
        }
    }


}
