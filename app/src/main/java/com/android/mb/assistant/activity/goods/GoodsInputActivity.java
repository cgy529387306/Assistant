package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资录入
 */
public class GoodsInputActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private EditText mEtName;
    private EditText mEtBrand;
    private EditText mEtAddress;
    private EditText mEtNumber;
    private EditText mEtPrice;
    private EditText mEtContact;
    private TextView mTvYes;
    private TextView mTvNo;
    private TextView mTvSelectCategory;
    private TextView mTvSelectCompany;
    private TextView mTvSelectDepartment;
    private TextView mTvSelectDate;
    private TextView mTvConfirm;
    private TimePickerView pvInputTime;
    private TextView mTvInputTime;

    private boolean isYes = false;

    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_input;
    }

    @Override
    protected void initTitle() {
        setTitleText("物资录入");
    }

    @Override
    protected void bindViews() {
        initView();
        initTimePicker();
    }

    private void initView(){
        mEtName = findViewById(R.id.et_name);
        mEtBrand = findViewById(R.id.et_brand);
        mEtAddress = findViewById(R.id.et_address);
        mEtNumber = findViewById(R.id.et_number);
        mEtPrice = findViewById(R.id.et_price);
        mEtContact = findViewById(R.id.et_contact);
        mTvYes = findViewById(R.id.tv_yes);
        mTvNo = findViewById(R.id.tv_no);
        mTvSelectCategory = findViewById(R.id.tv_select_category);
        mTvSelectCompany = findViewById(R.id.tv_select_company);
        mTvSelectDepartment = findViewById(R.id.tv_select_department);
        mTvSelectDate = findViewById(R.id.tv_select_date);
        mTvConfirm = findViewById(R.id.tv_confirm);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvYes.setOnClickListener(this);
        mTvNo.setOnClickListener(this);
        mTvSelectCategory.setOnClickListener(this);
        mTvSelectCompany.setOnClickListener(this);
        mTvSelectDepartment.setOnClickListener(this);
        mTvSelectDate.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_yes || id == R.id.tv_no){
            mTvYes.setBackgroundResource(isYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvNo.setBackgroundResource(isYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isYes = !isYes;
        }else if (id == R.id.tv_select_category){

        }else if (id == R.id.tv_select_company){

        }else if (id == R.id.tv_select_department){

        }else if (id == R.id.tv_select_date){
            pvInputTime.show(view);
        }else if (id == R.id.tv_confirm){
            ProjectHelper.disableViewDoubleClick(view);
            doSubmit();
        }
    }

    private void doSubmit(){
        String name = mEtName.getText().toString();
        String brand = mEtBrand.getText().toString();
        String address = mEtAddress.getText().toString();
        String number = mEtNumber.getText().toString();
        String price = mEtPrice.getText().toString();
        String contact = mEtContact.getText().toString();
        if (Helper.isEmpty(name)){
            showToastMessage("请输入物资名称");
            return;
        }
        if (Helper.isEmpty(brand)){
            showToastMessage("请输入物资型号");
            return;
        }
        if (Helper.isEmpty(address)){
            showToastMessage("请输入存放位置");
            return;
        }
        if (Helper.isEmpty(number)){
            showToastMessage("请输入物资数量");
            return;
        }
        if (Helper.isEmpty(price)){
            showToastMessage("请输入物资单价");
            return;
        }
        if (Helper.isEmpty(contact)){
            showToastMessage("请输入物资联系人");
            return;
        }

        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("materialName",name);
        requestParams.put("materialType","1");
        requestParams.put("pattern",brand);
        requestParams.put("unitName","春华科教园");
        requestParams.put("storageSite",address);
        requestParams.put("mum",number);
        requestParams.put("asset","1");
        requestParams.put("dePartment","设计部");
        requestParams.put("createTime","20190701225700");
        requestParams.put("contacts",contact);
        requestParams.put("imgStr","无");
        mPresenter.requestData(CodeConstants.KEY_GOODS_ADD,requestParams,true);

    }

    @Override
    public void requestSuccess(String requestCode,String result) {
        CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
        if (resp!=null){
            if (resp.isSuccess()){
                sendMsg(ProjectConstants.EVENT_UPDATE_COMPETITIVE,null);
                showToastMessage("录入成功");
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

    private void initTimePicker() {
        mTvInputTime.setText(Helper.date2String(new Date(),"yyyy-MM-dd HH:mm:00"));
        pvInputTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvInputTime.setText(Helper.date2String(date,"yyyy-MM-dd HH:mm:00"));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("请选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(0xFF333333)//标题文字颜色
                .setSubmitColor(0xFF2aaeff)//确定按钮文字颜色
                .setCancelColor(0xFF2aaeff)//取消按钮文字颜色
                .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
    }
}
