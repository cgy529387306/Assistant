package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;

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
        }
    }
}
