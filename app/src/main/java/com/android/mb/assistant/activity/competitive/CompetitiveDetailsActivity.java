package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;

import java.util.HashMap;
import java.util.Map;

/**
 * 竞情详情
 */
public class CompetitiveDetailsActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private CompetitiveBean mCompetitiveBean;

    private TextView mTvName,mTvTel,mTvAddress;
    private TextView mTvTelNum,mTvCover,mTvNetwork,mTvOperator,mTvTogether,mTvDueTime,mTvInputTime;
    private TextView mTvConfirm;

    @Override
    protected void loadIntent() {
        mCompetitiveBean = (CompetitiveBean) getIntent().getSerializableExtra("cometitive");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competitive_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("竞情详情");
    }

    @Override
    protected void bindViews() {
        mTvName = findViewById(R.id.tv_name);
        mTvTel = findViewById(R.id.tv_tel);
        mTvAddress = findViewById(R.id.tv_address);
        mTvTelNum = findViewById(R.id.tv_tel_num);
        mTvCover = findViewById(R.id.tv_cover);
        mTvNetwork = findViewById(R.id.tv_network);
        mTvOperator = findViewById(R.id.tv_operator);
        mTvTogether = findViewById(R.id.tv_together);
        mTvDueTime = findViewById(R.id.tv_due_time);
        mTvInputTime = findViewById(R.id.tv_input_time);
        mTvConfirm = findViewById(R.id.tv_confirm);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initData();
    }

    @Override
    protected void setListener() {
        mTvConfirm.setOnClickListener(this);
    }

    private void initData(){
        if (mCompetitiveBean!=null){
            mTvName.setText(mCompetitiveBean.getCUsername());
            mTvTel.setText(mCompetitiveBean.getcAddMoblie());
            mTvAddress.setText(mCompetitiveBean.getCAdd());
            mTvTelNum.setText(String.valueOf(mCompetitiveBean.getCNum()));
            mTvCover.setText(mCompetitiveBean.getCIsOverlap()==0?"是":"否");
            mTvNetwork.setText(mCompetitiveBean.getCIsBroadband()==0?"是":"否");
            mTvOperator.setText(mCompetitiveBean.getCIsOverlap()==0?"电信":"联通");
            mTvTogether.setText(mCompetitiveBean.getCFuse()==0?"是":"否");
            mTvDueTime.setText(Helper.long2DateString(mCompetitiveBean.getCBecomeTime(),Helper.DATE_FORMAT1));
            mTvInputTime.setText(Helper.long2DateString(mCompetitiveBean.getCCreateTime(),Helper.DATE_FORMAT1));
        }
    }

    @Override
    public void requestSuccess(String result) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void requestData(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("cId",mCompetitiveBean.getCId());
        mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DETAIL,requestParams,false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm){
            NavigationHelper.startActivity(this, SelectPersonActivity.class,null,false);
        }
    }
}
