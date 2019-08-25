package com.android.mb.assistant.activity.goods;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.entitys.ApplyDetailResp;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CurrentUser;
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
    private ImageView mIvStatus1,mIvStatus2,mIvStatus3;
    private TextView mTvStatus1,mTvStatus2,mTvStatus3;
    private View mViewStatus1,mViewStatus2;
    private LinearLayout mLlOperate;
    private RecyclerView mRecyclerView;
    private GoodsApplyUseAdapter mAdapter;
    private ApplyBean mApplyBean;
    private int mType;//1：申请  0：审批
    @Override
    protected void loadIntent() {
        mType = getIntent().getIntExtra("type",0);
        mApplyBean = (ApplyBean) getIntent().getSerializableExtra("mApplyBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_details;
    }

    @Override
    protected void initTitle() {
        setTitleText(mType==1?"我的使用申请":"使用申请审批");
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
        mIvStatus1 = findViewById(R.id.iv_status1);
        mIvStatus2 = findViewById(R.id.iv_status2);
        mIvStatus3 = findViewById(R.id.iv_status3);
        mTvStatus1 = findViewById(R.id.tv_status1);
        mTvStatus2 = findViewById(R.id.tv_status2);
        mTvStatus3 = findViewById(R.id.tv_status3);
        mViewStatus1 = findViewById(R.id.v_status1);
        mViewStatus2 = findViewById(R.id.v_status2);
        mLlOperate = findViewById(R.id.lly_operate);
        mTvRevoke.setVisibility(mType==1||mType==2?View.VISIBLE:View.GONE);
        mLlOperate.setVisibility(mType==1||mType==2?View.GONE:View.VISIBLE);
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
            initStatus();
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_return){
            //退回
            doApprove(1,0);
        }else if (id == R.id.tv_refuse){
            //拒绝
            doApprove(3,0);
        }else if (id == R.id.tv_agree){
            //同意
            doApprove(3,1);
        }else if (id == R.id.tv_revoke){
            //撤回
            doRevoke();
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        if (CodeConstants.KEY_GOODS_APPLY_DETAIL.equals(requestCode)){
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
        }else{
            CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
            if (resp!=null){
                if (resp.isSuccess()){
                    showToastMessage("操作成功");
                    sendMsg(ProjectConstants.EVENT_UPDATE_APPLY,null);
                    getDetail();
                }else{
                    showToastMessage(resp.getMessage());
                }
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

    private void doApprove(int apStatus,int apSyStatus){
        if (mApplyBean!=null){
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("apStatus", apStatus+"");
            requestParams.put("apSyStatus",apSyStatus+"");
            requestParams.put("applicationId",mApplyBean.getApplicationId());
            mPresenter.requestApplicant(CodeConstants.KEY_GOODS_APPROVE,requestParams,true);
        }
    }

    private void doRevoke(){
        if (mApplyBean!=null){
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("apStatus", "1");
            requestParams.put("applicationId",mApplyBean.getApplicationId());
            mPresenter.requestApplicant(CodeConstants.KEY_GOODS_CANCEL,requestParams,true);
        }
    }

    private void initStatus(){
        //apStatus(申请状态   0:发起   1：撤回    2：审批中    3：审批完成)
        //apSyStatus(使用状态   0:同意     1：不同意退回)
        if (mApplyBean!=null){
            if (mApplyBean.getApStatus() == 3){
                mLlOperate.setVisibility(View.GONE);
            }else{
                mLlOperate.setVisibility(View.VISIBLE);
            }
            if (mApplyBean.getApStatus()==0){
                mIvStatus1.setImageResource(R.mipmap.icon_status_on);
                mTvStatus1.setText(mApplyBean.getApSqName()+"提交审批");
                mViewStatus2.setBackgroundColor(Color.parseColor("#E1E1E1"));

            }else if (mApplyBean.getApStatus()==2){
                mIvStatus1.setImageResource(R.mipmap.icon_status_on);
                mTvStatus1.setText(mApplyBean.getApSqName()+"提交审批");
                mViewStatus2.setBackgroundColor(Color.parseColor("#E1E1E1"));

            }else if (mApplyBean.getApStatus()==3){
                mIvStatus1.setImageResource(R.mipmap.icon_status_check);
                mTvStatus1.setText(mApplyBean.getApSqName()+"提交审批");
                if (mApplyBean.getApSyStatus()==0){
                    mTvStatus2.setText(mApplyBean.getApApprover()+"审批通过");
                    mIvStatus2.setImageResource(R.mipmap.icon_status_check);
                }else{
                    mTvStatus2.setText(mApplyBean.getApApprover()+"审批不通过");
                    mIvStatus2.setImageResource(R.mipmap.icon_status_check);
                }
                mViewStatus2.setBackgroundColor(Color.parseColor("#0080CC"));
                mIvStatus3.setImageResource(R.mipmap.icon_status_pass);
                mTvStatus3.setText("已完成");
            }
        }
    }


}
