package com.android.mb.assistant.activity.competitive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GridImageAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.widget.ClearableEditText;
import com.android.mb.assistant.widget.FullyGridLayoutManager;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 竞情录入
 */
public class CompetitiveInputActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvCoverYes;
    private TextView mTvCoverNo;
    private TextView mTvNetworkYes;
    private TextView mTvNetworkNo;
    private TextView mTvOperatorYes;
    private TextView mTvOperatorNo;
    private TextView mTvTogetherYes;
    private TextView mTvTogetherNo;
    private boolean isCoverYes = false;
    private boolean isNetworkYes = false;
    private boolean isOperatorYes = false;
    private boolean isTogetherYes = false;
    private EditText mEtAccount;
    private EditText mEtPhone;
    private EditText mEtAddress;
    private TextView mTvNum;
    private ImageView mIvReduce;
    private ImageView mIvAdd;
    private int num = 1;
    private TextView mTvUserId;
    private LinearLayout mLlyDueTime;
    private TextView mTvDueTime;
    private TimePickerView pvDueTime;
    private TimePickerView pvInputTime;
    private LinearLayout mLlyInputTime;
    private TextView mTvInputTime;
    private RecyclerView mRecyclerView;
    private GridImageAdapter mImageAdapter;
    private List<LocalMedia> mSelectImageList = new ArrayList<>();
    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competitive_input;
    }

    @Override
    protected void initTitle() {
        setTitleText("竞情录入");
    }

    @Override
    protected void bindViews() {
        initView();
        initRecycleView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvCoverYes.setOnClickListener(this);
        mTvCoverNo.setOnClickListener(this);
        mTvNetworkYes.setOnClickListener(this);
        mTvNetworkNo.setOnClickListener(this);
        mTvOperatorYes.setOnClickListener(this);
        mTvOperatorNo.setOnClickListener(this);
        mTvTogetherYes.setOnClickListener(this);
        mTvTogetherNo.setOnClickListener(this);
        mIvReduce.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mLlyDueTime.setOnClickListener(this);
        mLlyInputTime.setOnClickListener(this);
    }

    private void initView() {
        mTvCoverYes = findViewById(R.id.tv_cover_yes);
        mTvCoverNo = findViewById(R.id.tv_cover_no);
        mTvNetworkYes = findViewById(R.id.tv_network_yes);
        mTvNetworkNo = findViewById(R.id.tv_network_no);
        mTvOperatorYes = findViewById(R.id.tv_operator_yes);
        mTvOperatorNo = findViewById(R.id.tv_operator_no);
        mTvTogetherYes = findViewById(R.id.tv_together_yes);
        mTvTogetherNo = findViewById(R.id.tv_together_no);
        mEtAccount = findViewById(R.id.et_account);
        mEtPhone = findViewById(R.id.et_phone);
        mEtAddress = findViewById(R.id.et_address);
        mTvNum = findViewById(R.id.tv_num);
        mIvReduce = findViewById(R.id.iv_reduce);
        mIvAdd = findViewById(R.id.iv_add);
        num = Integer.parseInt(mTvNum.getText().toString());
        mTvUserId = findViewById(R.id.tv_userId);
        if(CurrentUser.getInstance().isLogin()) {
            mTvUserId.setText("员工号" + CurrentUser.getInstance().getUserid());
        }
        mLlyDueTime = findViewById(R.id.lly_due_time);
        mTvDueTime = findViewById(R.id.tv_due_time);
        mLlyInputTime = findViewById(R.id.lly_input_time);
        mTvInputTime = findViewById(R.id.tv_input_time);
        initTimePicker();
    }

    private void initRecycleView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        FullyGridLayoutManager gridLayoutManager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mImageAdapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                //拍照
                PictureSelector.create(CompetitiveInputActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .isCamera(true)
                        .maxSelectNum(9)
                        .compress(true)// 是否压缩 true or false
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        mImageAdapter.setList(mSelectImageList);
        mImageAdapter.setSelectMax(6);
        mRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (mSelectImageList.size() > 0 && mSelectImageList.size()>position) {
                    LocalMedia media = mSelectImageList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    if (mediaType == 1){
                        PictureSelector.create(CompetitiveInputActivity.this).externalPicturePreview(position, mSelectImageList);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_cover_yes || id == R.id.tv_cover_no){
            mTvCoverYes.setBackgroundResource(isCoverYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvCoverNo.setBackgroundResource(isCoverYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isCoverYes = !isCoverYes;
        }else if (id == R.id.tv_network_yes || id == R.id.tv_network_no){
            mTvNetworkYes.setBackgroundResource(isNetworkYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvNetworkNo.setBackgroundResource(isNetworkYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isNetworkYes = !isNetworkYes;
        }else if (id == R.id.tv_operator_yes || id == R.id.tv_operator_no){
            mTvOperatorYes.setBackgroundResource(isOperatorYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvOperatorNo.setBackgroundResource(isOperatorYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isOperatorYes = !isOperatorYes;
        }else if (id == R.id.tv_together_yes || id == R.id.tv_together_no){
            mTvTogetherYes.setBackgroundResource(isTogetherYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvTogetherNo.setBackgroundResource(isTogetherYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isTogetherYes = !isTogetherYes;
        }else if (id == R.id.iv_reduce){
            if (num > 1){
                num--;
            }
            mTvNum.setText(String.valueOf(num));
        }else if (id == R.id.iv_add){
            if (num < 3){
                num++;
            }
            mTvNum.setText(String.valueOf(num));
        }else if (id == R.id.lly_due_time){
            pvDueTime.show(view);
        }else if (id == R.id.lly_input_time){
            pvInputTime.show(view);
        }
    }

    private void initTimePicker() {
        pvDueTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvDueTime.setText(Helper.date2String(date,"yyyy-MM-dd HH:mm:00"));
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> images = PictureSelector.obtainMultipleResult(data);
                    mSelectImageList.addAll(images);
                    mImageAdapter.setList(mSelectImageList);
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
