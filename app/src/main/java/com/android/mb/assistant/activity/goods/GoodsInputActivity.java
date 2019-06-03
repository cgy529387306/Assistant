package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseActivity;

import org.w3c.dom.Text;

/**
 * 物资录入
 */
public class GoodsInputActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvYes;
    private TextView mTvNo;
    private LinearLayout mLlyDate;
    private TextView mTvDate;
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
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvYes.setOnClickListener(this);
        mTvNo.setOnClickListener(this);
    }

    private void initView() {
        mTvYes = findViewById(R.id.tv_yes);
        mTvNo = findViewById(R.id.tv_no);
        mLlyDate = findViewById(R.id.lly_date);
        mTvDate = findViewById(R.id.tv_date);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_yes || id == R.id.tv_no){
            mTvYes.setBackgroundResource(isYes?R.drawable.goods_input_border_left_blue:R.drawable.goods_input_border_left_gray);
            mTvNo.setBackgroundResource(isYes?R.drawable.goods_input_border_right_gray:R.drawable.goods_input_border_right_blue);
            isYes = !isYes;
        }else if (id == R.id.lly_date){

        }
    }
}
