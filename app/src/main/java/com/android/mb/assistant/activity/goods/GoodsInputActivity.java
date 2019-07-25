package com.android.mb.assistant.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveInputActivity;
import com.android.mb.assistant.activity.competitive.SelectCityActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CityBean;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.DicBean;
import com.android.mb.assistant.entitys.PatternBean;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资录入
 */
public class GoodsInputActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private EditText mEtName;
    private EditText mEtAddress;
    private EditText mEtNumber;
    private EditText mEtPrice;
    private EditText mEtContact;
    private TextView mTvYes;
    private TextView mTvNo;
    private TextView mTvSelectPattern;
    private TextView mTvSelectCategory;
    private TextView mTvSelectCompany;
    private TextView mTvSelectDepartment;
    private TextView mTvSelectDate;
    private TextView mTvConfirm;
    private TimePickerView pvInputTime;

    private boolean isYes = false;

    private static final int REQUEST_PATTERN = 0x11;
    private static final int REQUEST_CATEGORY = 0x22;
    private static final int REQUEST_COMPANY = 0x33;
    private static final int REQUEST_GS_DEP = 0x44;


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
        mEtAddress = findViewById(R.id.et_address);
        mEtNumber = findViewById(R.id.et_number);
        mEtPrice = findViewById(R.id.et_price);
        mEtContact = findViewById(R.id.et_contact);
        mTvYes = findViewById(R.id.tv_yes);
        mTvNo = findViewById(R.id.tv_no);
        mTvSelectPattern = findViewById(R.id.tv_select_pattern);
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
        mTvSelectPattern.setOnClickListener(this);
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
        }else if (id == R.id.tv_select_pattern){
            //选择型号
            NavigationHelper.startActivityForResult(GoodsInputActivity.this, SelectPatternActivity.class,null,REQUEST_PATTERN);
        }else if (id == R.id.tv_select_category){
            //选择类型
            NavigationHelper.startActivityForResult(GoodsInputActivity.this, SelectCategoryActivity.class,null,REQUEST_CATEGORY);
        }else if (id == R.id.tv_select_company){
            //选择保管单位
            NavigationHelper.startActivityForResult(GoodsInputActivity.this, SelectCompanyActivity.class,null,REQUEST_COMPANY);
        }else if (id == R.id.tv_select_department){
            //选择归属部门
            NavigationHelper.startActivityForResult(GoodsInputActivity.this, SelectGsDepActivity.class,null,REQUEST_GS_DEP);
        }else if (id == R.id.tv_select_date){
            pvInputTime.show(view);
        }else if (id == R.id.tv_confirm){
            ProjectHelper.disableViewDoubleClick(view);
            doSubmit();
        }
    }

    private void doSubmit(){
        String name = mEtName.getText().toString();
        String address = mEtAddress.getText().toString();
        String number = mEtNumber.getText().toString();
        String price = mEtPrice.getText().toString();
        String contact = mEtContact.getText().toString();
        String pattern = mTvSelectPattern.getText().toString();
        String category = mTvSelectCategory.getText().toString();
        String company = mTvSelectCompany.getText().toString();
        String department = mTvSelectDepartment.getText().toString();
        long inputTime = Helper.dateString2Long(mTvSelectDate.getText().toString().trim(),"yyyy-MM-dd HH:mm:00");
        if (Helper.isEmpty(name)){
            showToastMessage("请输入物资名称");
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
        if (Helper.isEmpty(pattern) || pattern.equals("请选择")){
            showToastMessage("请选择物资型号");
            return;
        }
        if (Helper.isEmpty(category) || category.equals("请选择")){
            showToastMessage("请选择物资类别");
            return;
        }
        if (Helper.isEmpty(company) || company.equals("请选择")){
            showToastMessage("请选择物资保管单位");
            return;
        }
        if (Helper.isEmpty(department) || department.equals("请选择")){
            showToastMessage("请选择物资归属部门");
            return;
        }

        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("materialName",name);
        requestParams.put("materialType",category);
        requestParams.put("pattern",pattern);
        requestParams.put("unitName",company);
        requestParams.put("storageSite",address);
        requestParams.put("mum",number);
        requestParams.put("asset",isYes?"1":"0");
        requestParams.put("dePartment",department);
        requestParams.put("createTime",String.valueOf(inputTime));
        requestParams.put("contacts",contact);
//        requestParams.put("imgStr","无");
        mPresenter.requestGoods(CodeConstants.KEY_GOODS_ADD,requestParams,true);

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
        mTvSelectDate.setText(Helper.date2String(new Date(),"yyyy-MM-dd HH:mm:00"));
        pvInputTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvSelectDate.setText(Helper.date2String(date,"yyyy-MM-dd HH:mm:00"));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PATTERN:
                    PatternBean item = (PatternBean) data.getSerializableExtra("pattern");
                    mTvSelectPattern.setText(item.getPatternName());
                    break;
                case REQUEST_CATEGORY:
                    DicBean category = (DicBean) data.getSerializableExtra("dic");
                    mTvSelectCategory.setText(category.getDictname());
                    break;
                case REQUEST_COMPANY:
                    DicBean company = (DicBean) data.getSerializableExtra("dic");
                    mTvSelectCompany.setText(company.getDictname());
                    break;
                case REQUEST_GS_DEP:
                    DicBean department = (DicBean) data.getSerializableExtra("dic");
                    mTvSelectDepartment.setText(department.getDictname());
                    break;

                default:
                    break;
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
