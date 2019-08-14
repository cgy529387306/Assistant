package com.android.mb.assistant.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CartBean;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.UserBean;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.AppHelper;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.utils.ToastHelper;
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
    private String mApprovalName;
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
        mEtName.setText(CurrentUser.getInstance().getUname());
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
            NavigationHelper.startActivityForResult(this, SelectApprovalActivity.class,null,1001);
        }
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
        if (resp!=null){
            if (resp.isSuccess()){
                sendMsg(ProjectConstants.EVENT_UPDATE_CART,null);
                ToastHelper.showToast("申请成功");
                finish();
            }else{
                showToastMessage(resp.getMessage());
            }
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    private void doSubmit(){
        String tel = mEtTel.getText().toString();
        String name = mEtName.getText().toString();
        String remark = mEtRemark.getText().toString();
        if (Helper.isEmpty(tel)){
            showToastMessage("请输入手机号码");
            return;
        }
        if (!ProjectHelper.isMobiPhoneNum(tel)){
            showToastMessage("手机号码输入有误");
            return;
        }
        if (Helper.isEmpty(mApprovalName)){
            showToastMessage("请选择审批人");
            return;
        }
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("userId", CurrentUser.getInstance().getUid());
        requestParams.put("materialIds",getMaterialIds(mCartBeanList));
        requestParams.put("phone", tel);
        requestParams.put("apRemarks", remark);
        requestParams.put("apApprover", mApprovalName);
        mPresenter.requestCart(CodeConstants.KEY_CART_APPLY,requestParams,true);
    }


    private String getMaterialIds(List<CartBean> dataList){
        if (Helper.isEmpty(dataList)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            CartBean cartBean = dataList.get(i);
            sb.append(cartBean.getMaterialId()).append(",");

        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1001 && data!=null){
                UserBean userBean = (UserBean) data.getSerializableExtra("userBean");
                mSelectApproval.setText(userBean.getUname());
                mApprovalName = userBean.getUname();
            }
        }
    }


    /**
     * 点击非编辑区域收起键盘
     * 获取点击事件
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN ) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
