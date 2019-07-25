package com.android.mb.assistant.activity.competitive;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.ImageListAdapter;
import com.android.mb.assistant.adapter.YyTelAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.entitys.CompetitiveResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.rxbus.Events;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.FullyGridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * 竞情详情
 */
public class CompetitiveDetailsActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private CompetitiveBean mCompetitiveBean;

    private TextView mTvName,mTvTel,mTvAddress;
    private TextView mTvTelNum,mTvCover,mTvNetwork,mTvOperator,mTvTogether,mTvDueTime;
    private TextView mTvCity,mTvDep,mTvPerson,mTvInputTime,mTvRemark;
    private TextView mTvConfirm;
    private ImageView mIvStatus1,mIvStatus2,mIvStatus3;
    private TextView mTvStatus1,mTvStatus2,mTvStatus3;
    private View mViewStatus1,mViewStatus2;
    private RecyclerView mRvTel;
    private YyTelAdapter mTelAdapter;

    private RecyclerView mRecyclerView;
    private ImageListAdapter mImageAdapter;
    @Override
    protected void loadIntent() {
        mCompetitiveBean = (CompetitiveBean) getIntent().getSerializableExtra("competitive");
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
        mRvTel = findViewById(R.id.rv_tel);
        mRvTel.setLayoutManager(new LinearLayoutManager(this));
        mTelAdapter = new YyTelAdapter(new ArrayList());
        mRvTel.setAdapter(mTelAdapter);

        mRecyclerView = findViewById(R.id.recyclerView);
        FullyGridLayoutManager gridLayoutManager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mImageAdapter = new ImageListAdapter(new ArrayList());
        mRecyclerView.setAdapter(mImageAdapter);

        mIvStatus1 = findViewById(R.id.iv_status1);
        mIvStatus2 = findViewById(R.id.iv_status2);
        mIvStatus3 = findViewById(R.id.iv_status3);
        mTvStatus1 = findViewById(R.id.tv_status1);
        mTvStatus2 = findViewById(R.id.tv_status2);
        mTvStatus3 = findViewById(R.id.tv_status3);
        mViewStatus1 = findViewById(R.id.v_status1);
        mViewStatus2 = findViewById(R.id.v_status2);
        mTvName = findViewById(R.id.tv_name);
        mTvTel = findViewById(R.id.tv_tel);
        mTvAddress = findViewById(R.id.tv_address);
        mTvTelNum = findViewById(R.id.tv_tel_num);
        mTvCover = findViewById(R.id.tv_cover);
        mTvNetwork = findViewById(R.id.tv_network);
        mTvOperator = findViewById(R.id.tv_operator);
        mTvTogether = findViewById(R.id.tv_together);
        mTvDueTime = findViewById(R.id.tv_due_time);
        mTvCity = findViewById(R.id.tv_city);
        mTvDep = findViewById(R.id.tv_dep);
        mTvPerson = findViewById(R.id.tv_person);
        mTvInputTime = findViewById(R.id.tv_input_time);
        mTvRemark = findViewById(R.id.tv_remark);
        mTvConfirm = findViewById(R.id.tv_confirm);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initData();
        initStatus();
    }

    @Override
    protected void setListener() {
        findViewById(R.id.tv_call).setOnClickListener(this);
        findViewById(R.id.tv_navigation).setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
        regiestEvent(ProjectConstants.EVENT_UPDATE_COMPETITIVE, new Action1<Events<?>>() {
            @Override
            public void call(Events<?> events) {
                requestData();
            }
        });
        mImageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mImageAdapter.getData().size() > 0 && mImageAdapter.getData().size()>position) {
                    List<LocalMedia> dataList = new ArrayList<>();
                    for (String imageUrl:mImageAdapter.getData()){
                        LocalMedia media = new LocalMedia();
                        media.setPath(imageUrl);
                        media.setCompressed(false);
                        media.setPictureType("image/jpeg");
                        dataList.add(media);
                    }
                    PictureSelector.create(CompetitiveDetailsActivity.this).externalPicturePreview(position, dataList);
                }
            }
        });
    }

    private void initData(){
        if (mCompetitiveBean!=null){
            mTvName.setText(mCompetitiveBean.getCUsername());
            mTvTel.setText(mCompetitiveBean.getCMobile());
            mTvAddress.setText(mCompetitiveBean.getCAdd());
            mTvTelNum.setText(String.valueOf(mCompetitiveBean.getCNum()));
            mTvCover.setText(mCompetitiveBean.getCIsOverlap()==0?"是":"否");
            mTvNetwork.setText(mCompetitiveBean.getCIsBroadband()==0?"是":"否");
            mTvOperator.setText(mCompetitiveBean.getCIsOverlap()==0?"电信":"联通");
            mTvTogether.setText(mCompetitiveBean.getCFuse()==0?"是":"否");
            mTvCity.setText(mCompetitiveBean.getcAreaName());
            mTvDep.setText(mCompetitiveBean.getcDepartmentName());
            mTvPerson.setText(mCompetitiveBean.getCOpName());
            mTvRemark.setText(Helper.isEmpty(mCompetitiveBean.getCRemarks())?"无":mCompetitiveBean.getCRemarks());
            mTvDueTime.setText(Helper.long2DateString(mCompetitiveBean.getCBecomeTime(),Helper.DATE_FORMAT1));
            mTvInputTime.setText(Helper.long2DateString(mCompetitiveBean.getCCreateTime(),Helper.DATE_FORMAT1));
            if (mCompetitiveBean.getCNum()>0 && Helper.isNotEmpty(mCompetitiveBean.getcAddMoblie())){
                List<String> telList = ProjectHelper.strToList(mCompetitiveBean.getcAddMoblie());
                mTelAdapter.setNewData(telList);
            }
            if (Helper.isNotEmpty(mCompetitiveBean.getcSituationImg())){
                List<String> imageList = ProjectHelper.strToList(mCompetitiveBean.getcSituationImg());
                mImageAdapter.setNewData(imageList);
            }
        }
    }

    private void initStatus(){
        if (mCompetitiveBean!=null){
            if (mCompetitiveBean.getCDispatchStatus()==1){
                mIvStatus1.setImageResource(R.mipmap.icon_status_on);
                mTvStatus1.setText("未派单");
                mViewStatus2.setBackgroundColor(Color.parseColor("#E1E1E1"));
            }else if (mCompetitiveBean.getCDispatchStatus()==2){
                mIvStatus1.setImageResource(R.mipmap.icon_status_check);
                mTvStatus1.setText("已派单");
                mViewStatus2.setBackgroundColor(Color.parseColor("#E1E1E1"));
                if (mCompetitiveBean.getCHandlingStatus()==1){
                    mIvStatus2.setImageResource(R.mipmap.icon_status_off);
                    mTvStatus2.setText("未处理");
                }else{
                    mIvStatus2.setImageResource(R.mipmap.icon_status_check);
                    mTvStatus2.setText("已处理");
                }
            }else if (mCompetitiveBean.getCDispatchStatus()==3){
                mIvStatus1.setImageResource(R.mipmap.icon_status_check);
                mTvStatus1.setText("已派单");
                mIvStatus2.setImageResource(R.mipmap.icon_status_check);
                mTvStatus2.setText("已处理");
                mViewStatus2.setBackgroundColor(Color.parseColor("#0080CC"));
                mIvStatus3.setImageResource(R.mipmap.icon_status_pass);
                mTvStatus3.setText("反抢成功");
            }
        }
    }

    @Override
    public void requestSuccess(String requestCode,String result) {
        CompetitiveResp resp = JsonHelper.fromJson(result,CompetitiveResp.class);
        if (resp!=null){
            if (resp.isSuccess() && resp.getData()!=null){
                mCompetitiveBean = resp.getData();
                initData();
                initStatus();
            }else{
                showToastMessage(resp.getMessage());
            }
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void requestData(){
        if (mCompetitiveBean!=null){
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("cId",mCompetitiveBean.getCId());
            mPresenter.requestData(CodeConstants.KEY_COMPETITIVE_DETAIL,requestParams,false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm){
            Bundle bundle = new Bundle();
            bundle.putString("competitiveId",mCompetitiveBean.getCId());
            NavigationHelper.startActivity(this, SelectPersonActivity.class,bundle,false);
        }else if (id == R.id.tv_call){
            if (mCompetitiveBean!=null && Helper.isNotEmpty(mCompetitiveBean.getCMobile())){
                ProjectHelper.callPhone(mContext,mCompetitiveBean.getCMobile());
            }
        }else if (id == R.id.tv_navigation){
            if (mCompetitiveBean!=null && Helper.isNotEmpty(mCompetitiveBean.getCAdd())){
                ProjectHelper.navWithMap(mContext,mCompetitiveBean.getCAdd());
            }
        }
    }
}
