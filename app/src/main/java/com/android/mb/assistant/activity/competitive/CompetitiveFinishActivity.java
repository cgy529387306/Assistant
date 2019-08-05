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
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GridImageAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CityBean;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 完成工单
 */
public class CompetitiveFinishActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private CompetitiveBean mCompetitiveBean;
    private TextView mTvYes;
    private TextView mTvNo;
    private TextView mTvConfirm;
    private boolean mIsSuccess = true;

    private RecyclerView mRecyclerView;
    private GridImageAdapter mImageAdapter;
    private List<LocalMedia> mSelectImageList = new ArrayList<>();

    @Override
    protected void loadIntent() {
        mCompetitiveBean = (CompetitiveBean) getIntent().getSerializableExtra("competitive");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competitive_finish;
    }

    @Override
    protected void initTitle() {
        setTitleText("完成工单");
    }

    @Override
    protected void bindViews() {
        initView();
        initRecycleView();
    }

    private void initView(){
        mTvYes = findViewById(R.id.tv_yes);
        mTvNo = findViewById(R.id.tv_no);
        mTvConfirm = findViewById(R.id.tv_confirm);
    }

    private void initRecycleView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        FullyGridLayoutManager gridLayoutManager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mImageAdapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                //拍照
                PictureSelector.create(CompetitiveFinishActivity.this)
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
                        PictureSelector.create(CompetitiveFinishActivity.this).externalPicturePreview(position, mSelectImageList);
                    }
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvYes.setOnClickListener(this);
        mTvNo.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
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
                    uploadImageList(images);
                    break;
            }
        }
    }

    private void uploadImageList(List<LocalMedia> dataList){
        for (LocalMedia localMedia:dataList) {
            String imagePath = localMedia.isCompressed()?localMedia.getCompressPath():localMedia.getPath();
            mPresenter.uploadImg(CodeConstants.KEY_COMMON_UPLOAD,new HashMap<>(),new File(imagePath),true);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_yes){
            mIsSuccess = true;
            mTvYes.setBackgroundResource(mIsSuccess?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvNo.setBackgroundResource(mIsSuccess?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
        }else if (id == R.id.tv_no){
            mIsSuccess = false;
            mTvYes.setBackgroundResource(mIsSuccess?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvNo.setBackgroundResource(mIsSuccess?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
        }else if (id == R.id.tv_confirm){
            ProjectHelper.disableViewDoubleClick(v);
            doConfirm();
        }
    }

    private void doConfirm(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("workorderId",mCompetitiveBean.getWorkorderId());
        requestParams.put("status",mIsSuccess?"3":"4");
        mPresenter.requestData(CodeConstants.KEY_COMPLETE_WORK,requestParams,true);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
        if (resp!=null){
            if (resp.isSuccess()){
                showToastMessage("操作成功");
                sendMsg(ProjectConstants.EVENT_UPDATE_COMPETITIVE,null);
                finish();
            }else{
                showToastMessage(resp.getMessage());
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
